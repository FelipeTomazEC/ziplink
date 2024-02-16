package com.github.felipetomazec.ziplink.utils

import net.datafaker.Faker
import spock.lang.Specification

class URLHashGeneratorTest extends Specification {
    def faker = new Faker()

    def "Generate hash"() {
        given:
        def url = faker.internet().url()
        def hashSize = 13

        when:
        def output1 = URLHashGenerator.hash(url, hashSize)

        then:
        output1.size() == hashSize

        when: "input is the same"
        def output2 = URLHashGenerator.hash(url, hashSize)

        then:
        output1 == output2
    }
}
