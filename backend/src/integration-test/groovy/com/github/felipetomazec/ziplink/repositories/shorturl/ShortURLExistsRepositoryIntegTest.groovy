package com.github.felipetomazec.ziplink.repositories.shorturl

import com.github.felipetomazec.ziplink.BaseIntegTest
import com.github.felipetomazec.ziplink.entities.ShortURL
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired

class ShortURLExistsRepositoryIntegTest extends BaseIntegTest {
    def faker = new Faker()

    @Autowired
    ShortURLExistsRepository sut

    def "Entity exists in db"() {
        given:
        def id = "short_123"
        def entity = new ShortURL(id, faker.internet().url())
        shortUrlJpaRepository.save(entity)

        when:
        def result = sut.exists(id)

        then:
        result
    }

    def "Entity does not exist in db"() {
        given:
        def id = "short_123"

        when:
        def result = sut.exists(id)

        then:
        !result
    }
}
