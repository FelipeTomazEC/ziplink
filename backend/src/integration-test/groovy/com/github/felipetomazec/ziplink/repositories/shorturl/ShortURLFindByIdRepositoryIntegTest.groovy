package com.github.felipetomazec.ziplink.repositories.shorturl

import com.github.felipetomazec.ziplink.BaseIntegTest
import com.github.felipetomazec.ziplink.entities.ShortURL
import com.github.felipetomazec.ziplink.repositories.FindByIdRepository
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class ShortURLFindByIdRepositoryIntegTest extends BaseIntegTest {
    def faker = new Faker()

    @Autowired
    ShortUrlJpaRepository jpaRepository

    @Subject
    @Autowired
    FindByIdRepository<ShortURL, String> sut

    def "Retrieve entity from db"() {
        given: "entity exists in db"
        def code = "123456ff%"
        def originalUrl = faker.internet().url()
        def entity = new ShortURL(code, originalUrl)
        jpaRepository.save(entity)

        when:
        def output = sut.findById(code)

        then:
        output.isPresent()
        output.get().shortUrl == code
        output.get().longUrl == originalUrl
    }

    def "Short url id does not exist"() {
        when:
        def output = sut.findById("invalid_id")

        then:
        output.isEmpty()
    }
}
