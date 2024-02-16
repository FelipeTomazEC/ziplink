package com.github.felipetomazec.ziplink.usecases

import com.github.felipetomazec.ziplink.entities.ShortURL
import com.github.felipetomazec.ziplink.repositories.ExistsRepository
import com.github.felipetomazec.ziplink.repositories.SaveRepository
import com.github.felipetomazec.ziplink.usecases.generateshorturl.GenerateShortUrlInput
import com.github.felipetomazec.ziplink.usecases.generateshorturl.GenerateShortUrlUseCase
import com.github.felipetomazec.ziplink.utils.URLHashGenerator
import net.datafaker.Faker
import spock.lang.Specification
import spock.lang.Subject

class GenerateShortUrlUseCaseTest extends Specification {
    def faker = new Faker()
    def existsRepository = Mock(ExistsRepository<ShortURL, String>)
    def saveRepository = Mock(SaveRepository<ShortURL>)

    @Subject
    def sut = new GenerateShortUrlUseCase(existsRepository, saveRepository)

    def "Short url generation"() {
        given:
        def longUrl = faker.internet().url()
        def input = new GenerateShortUrlInput(longUrl)

        and:
        1 * existsRepository.exists(_) >> false

        when:
        def output = sut.execute(input)

        then:
        output.shortUrl().size() == GenerateShortUrlUseCase.HASH_SIZE

        and:
        1 * saveRepository.save({ ShortURL it ->
            it.longUrl == input.longUrl()
            it.shortUrl == URLHashGenerator.hash(input.longUrl(), 12)
        })
    }

    def "Operation is idempotent"() {
        given:
        def longUrl = faker.internet().url()
        def input = new GenerateShortUrlInput(longUrl)

        and: "short url already exists"
        def hashSize = GenerateShortUrlUseCase.HASH_SIZE
        existsRepository.exists(URLHashGenerator.hash(longUrl, hashSize)) >> true

        when:
        def output = sut.execute(input)

        then:
        Objects.nonNull(output)
        0 * saveRepository.save(_)
    }
}
