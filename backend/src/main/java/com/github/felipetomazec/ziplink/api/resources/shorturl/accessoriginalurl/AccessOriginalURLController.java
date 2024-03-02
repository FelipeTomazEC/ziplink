package com.github.felipetomazec.ziplink.api.resources.shorturl.accessoriginalurl;

import com.github.felipetomazec.ziplink.api.resources.shorturl.ShortURLEndpointsV1;
import com.github.felipetomazec.ziplink.usecases.UseCase;
import com.github.felipetomazec.ziplink.usecases.accessoriginalurl.AccessOriginalURLInput;
import com.github.felipetomazec.ziplink.usecases.accessoriginalurl.AccessOriginalURLOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class AccessOriginalURLController {

    private final UseCase<AccessOriginalURLInput, AccessOriginalURLOutput> useCase;

    @GetMapping(value = ShortURLEndpointsV1.ACCESS_ORIGINAL_URL)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ResponseEntity<Void> handle(@PathVariable("shortUrlCode") String shortCodeUrl) {
        var input = new AccessOriginalURLInput(shortCodeUrl);
        var output = useCase.execute(input);
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, output.longUrl());

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
