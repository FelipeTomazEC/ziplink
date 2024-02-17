package com.github.felipetomazec.ziplink

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.PostgreSQLContainer

@Configuration
@ActiveProfiles("integration-test")
class IntegrationTestConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer postgreSQLContainer() {
        return new PostgreSQLContainer("postgres:15-alpine")
                .withDatabaseName("ziplink")
                .withUsername("integration_tests_user")
                .withPassword("integration_tests_password")
    }
}
