package com.github.felipetomazec.ziplink.api.resources.shorturl.generate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateShortURLHttpRequest {
    @NotBlank(message = "url cannot be null/blank.")
    private String url;
}
