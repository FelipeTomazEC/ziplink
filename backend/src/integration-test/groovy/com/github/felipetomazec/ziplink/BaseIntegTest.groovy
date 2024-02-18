package com.github.felipetomazec.ziplink

import com.github.felipetomazec.ziplink.repositories.shorturl.ShortUrlJpaRepository
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

import static io.restassured.RestAssured.config
import static io.restassured.RestAssured.given
import static io.restassured.config.EncoderConfig.encoderConfig

@Testcontainers
@ActiveProfiles("integration-test")
@SpringBootTest(classes = ZipLinkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegTest extends Specification {
    @Autowired
    ShortUrlJpaRepository shortUrlJpaRepository

    @LocalServerPort
    int port

    def request() {
        return given()
                .port(port)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(ContentType.JSON)
                .config(config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .log().all()
    }

    def cleanup() {
        shortUrlJpaRepository.deleteAll()
    }
}
