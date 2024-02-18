package com.github.felipetomazec.ziplink.usecases

import com.github.felipetomazec.ziplink.config.ShortURLConfig
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
    def config = new ShortURLConfig("https://ziplink.com/", 12)

    @Subject
    def sut = new GenerateShortUrlUseCase(
            existsRepository,
            saveRepository,
            config
    )

    def "Short url generation"() {
        given:
        def longUrl = faker.internet().url()
        def input = new GenerateShortUrlInput(longUrl)

        and:
        1 * existsRepository.exists(_) >> false

        when:
        def output = sut.execute(input)

        then:
        output.shortUrl().startsWith(config.baseUrl)
        output.shortUrl().split("/").last().size() == config.length

        and:
        1 * saveRepository.save({ ShortURL it ->
            it.longUrl == input.longUrl()
            it.shortUrl == URLHashGenerator.hash(input.longUrl(), config.length)
        })
    }

    def "Operation is idempotent"() {
        given:
        def longUrl = faker.internet().url()
        def input = new GenerateShortUrlInput(longUrl)

        and: "short url already exists"
        def hash = URLHashGenerator.hash(longUrl, config.length)
        existsRepository.exists(hash) >> true

        when:
        def output = sut.execute(input)

        then:
        Objects.nonNull(output)
        0 * saveRepository.save(_)
    }
}
