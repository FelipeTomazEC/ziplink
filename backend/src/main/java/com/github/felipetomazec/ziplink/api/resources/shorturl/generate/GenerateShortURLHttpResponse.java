package com.github.felipetomazec.ziplink.api.resources.shorturl.generate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenerateShortURLHttpResponse {
    private String shortUrl;
}
