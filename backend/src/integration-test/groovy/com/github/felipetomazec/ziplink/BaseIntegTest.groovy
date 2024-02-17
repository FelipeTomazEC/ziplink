package com.github.felipetomazec.ziplink

import com.github.felipetomazec.ziplink.repositories.shorturl.ShortUrlJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
@ActiveProfiles("integration-test")
@SpringBootTest(classes = ZipLinkApplication.class)
class BaseIntegTest extends Specification {
    @Autowired
    ShortUrlJpaRepository shortUrlJpaRepository

    def cleanup() {
        shortUrlJpaRepository.deleteAll()
    }
}
