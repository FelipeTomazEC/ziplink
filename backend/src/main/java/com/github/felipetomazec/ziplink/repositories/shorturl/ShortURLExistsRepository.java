package com.github.felipetomazec.ziplink.repositories.shorturl;

import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.ExistsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShortURLExistsRepository implements ExistsRepository<ShortURL, String> {

    private final ShortUrlJpaRepository repository;

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
