package com.github.felipetomazec.ziplink.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "ziplink.short-url-config")
public class ShortURLConfig {
    private String baseUrl;
    private Integer length;
}
