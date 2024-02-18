package com.github.felipetomazec.ziplink.api.resources.shorturl.generate;

import com.github.felipetomazec.ziplink.api.resources.shorturl.ShortURLEndpointsV1;
import com.github.felipetomazec.ziplink.usecases.generateshorturl.GenerateShortUrlInput;
import com.github.felipetomazec.ziplink.usecases.generateshorturl.GenerateShortUrlUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ShortURLEndpointsV1.CREATE)
public class GenerateShortUrlController {

    private final GenerateShortUrlUseCase useCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenerateShortURLHttpResponse handle(@Valid @RequestBody GenerateShortURLHttpRequest request) {
        var input = new GenerateShortUrlInput(request.getUrl());
        var output = useCase.execute(input);
        return new GenerateShortURLHttpResponse(output.shortUrl());
    }
}
