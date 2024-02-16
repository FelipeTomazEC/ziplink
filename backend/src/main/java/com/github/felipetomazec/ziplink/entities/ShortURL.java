package com.github.felipetomazec.ziplink.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortURL {
    private String shortUrl;
    private String longUrl;
}
