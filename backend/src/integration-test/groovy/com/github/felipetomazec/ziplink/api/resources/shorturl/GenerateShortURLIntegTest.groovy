package com.github.felipetomazec.ziplink.api.resources.shorturl

import com.github.felipetomazec.ziplink.BaseIntegTest
import com.github.felipetomazec.ziplink.api.resources.shorturl.generate.GenerateShortURLHttpRequest
import net.datafaker.Faker
import org.springframework.http.HttpStatus

class GenerateShortURLIntegTest extends BaseIntegTest {
    def faker = new Faker()

    def "A new short url is created"() {
        given:
        def longUrl = faker.internet().url()

        when:
        def response = request()
            .with().body(new GenerateShortURLHttpRequest(url: longUrl))
            .when().post(ShortURLEndpointsV1.CREATE)

        then:
        response.statusCode == HttpStatus.CREATED.value()
    }

    def "Request is valid"() {
        when:
        def response = request()
            .with().body(new GenerateShortURLHttpRequest(url: url))
            .when().post(ShortURLEndpointsV1.CREATE)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST.value()
        response.asPrettyString().contains("url is invalid")

        where:
        url << [null, ""]
    }
}
