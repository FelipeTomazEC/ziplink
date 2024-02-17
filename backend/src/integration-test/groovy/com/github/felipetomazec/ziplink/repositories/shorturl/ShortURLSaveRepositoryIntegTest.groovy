package com.github.felipetomazec.ziplink.repositories.shorturl

import com.github.felipetomazec.ziplink.BaseIntegTest
import com.github.felipetomazec.ziplink.entities.ShortURL
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class ShortURLSaveRepositoryIntegTest extends BaseIntegTest {
    @Subject
    @Autowired
    ShortURLSaveRepository sut

    def faker = new Faker()

    def "Save short url in db"() {
        given:
        def entity = new ShortURL(shortUrl: "short_123", longUrl: faker.internet().url())

        when:
        sut.save(entity)

        then:
        shortUrlJpaRepository.existsById(entity.shortUrl)
    }
}
