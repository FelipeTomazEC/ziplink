package com.github.felipetomazec.ziplink.repositories.shorturl;

import com.github.felipetomazec.ziplink.entities.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlJpaRepository extends JpaRepository<ShortURL, String> {}
