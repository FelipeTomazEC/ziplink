package com.github.felipetomazec.ziplink.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "short_url")
public class ShortURL {
    @Id
    private String shortUrl;
    private String longUrl;
}
