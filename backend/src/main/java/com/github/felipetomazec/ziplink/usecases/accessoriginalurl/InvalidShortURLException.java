package com.github.felipetomazec.ziplink.usecases.accessoriginalurl;

public class InvalidShortURLException extends RuntimeException {
    public InvalidShortURLException(String url) {
        super(String.format("URL %s is not a valid.", url));
    }
}
