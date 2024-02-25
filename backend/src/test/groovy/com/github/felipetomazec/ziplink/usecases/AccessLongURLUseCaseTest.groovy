package com.github.felipetomazec.ziplink.usecases


import com.github.felipetomazec.ziplink.entities.ShortURL
import com.github.felipetomazec.ziplink.repositories.FindByIdRepository
import com.github.felipetomazec.ziplink.usecases.accesslongurl.AccessLongURLInput
import com.github.felipetomazec.ziplink.usecases.accesslongurl.AccessLongURLUseCase
import com.github.felipetomazec.ziplink.usecases.accesslongurl.InvalidShortURLException
import net.datafaker.Faker
import spock.lang.Specification
import spock.lang.Subject

class AccessLongURLUseCaseTest extends Specification {
    def faker = new Faker()
    def repository = Mock(FindByIdRepository<ShortURL, String>)

    @Subject
    def sut = new AccessLongURLUseCase(repository)

    def "Long url ist retrieved"() {
        given:
        def code = "abcd1234"

        and:
        def longURL = faker.internet().url()
        def dbEntry = new ShortURL(code, longURL)
        1 * repository.findById(code) >> Optional.of(dbEntry)

        when:
        def output = sut.execute(new AccessLongURLInput(code))

        then:
        output.longUrl() == longURL
    }

    def "Invalid short url"() {
        given:
        def code = "1245fff%"

        and:
        1 * repository.findById(code) >> Optional.empty()

        when:
        sut.execute(new AccessLongURLInput(code))

        then:
        def exception = thrown(InvalidShortURLException)
        exception.message.contains(code)
    }
}
