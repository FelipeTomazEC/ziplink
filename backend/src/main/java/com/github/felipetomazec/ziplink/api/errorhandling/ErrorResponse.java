package com.github.felipetomazec.ziplink.api.errorhandling;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ErrorResponse {
    private final Set<String> errors;

    public ErrorResponse() {
        this.errors = new HashSet<>();
    }

    public void addError(String message) {
        this.errors.add(message);
    }

    public Set<String> getErrors() {
        return Collections.unmodifiableSet(this.errors);
    }
}
