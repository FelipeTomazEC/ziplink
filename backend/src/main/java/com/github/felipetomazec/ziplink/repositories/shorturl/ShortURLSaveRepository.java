package com.github.felipetomazec.ziplink.repositories.shorturl;

import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.SaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShortURLSaveRepository implements SaveRepository<ShortURL> {

    private final ShortUrlJpaRepository repository;

    @Override
    public void save(ShortURL entity) {
        repository.save(entity);
    }
}
