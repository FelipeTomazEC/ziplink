package com.github.felipetomazec.ziplink.api.resources.shorturl

import com.github.felipetomazec.ziplink.BaseIntegTest
import com.github.felipetomazec.ziplink.entities.ShortURL
import com.github.felipetomazec.ziplink.repositories.SaveRepository
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus


class AccessLongURLIntegTest extends BaseIntegTest {
    @Autowired
    SaveRepository<ShortURL> repository

    def faker = new Faker()

    def "Redirect to original URL"() {
        given: "short url exists in db"
        def shortCode = "1234f558Hg"
        def originalUrl = faker.internet().url()
        repository.save(new ShortURL(shortCode, originalUrl))

        when:
        def response = request()
                .with().pathParam("shortUrlCode", shortCode)
                .redirects().follow(false)
                .when().get(ShortURLEndpointsV1.ACCESS_ORIGINAL_URL)

        then:
        response.statusCode == HttpStatus.MOVED_PERMANENTLY.value()
        response.headers().get(HttpHeaders.LOCATION).getValue() == originalUrl
    }

    def "Invalid short code"() {
        when:
        def response = request()
                .with().pathParam("shortUrlCode", "invalid_url")
                .when().get(ShortURLEndpointsV1.ACCESS_ORIGINAL_URL)

        then:
        response.statusCode == HttpStatus.NOT_FOUND.value()
    }
}
