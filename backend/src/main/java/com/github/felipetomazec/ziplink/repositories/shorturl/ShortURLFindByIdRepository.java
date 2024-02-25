package com.github.felipetomazec.ziplink.repositories.shorturl;

import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.FindByIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ShortURLFindByIdRepository implements FindByIdRepository<ShortURL, String> {

    private final ShortUrlJpaRepository jpaRepository;

    @Override
    public Optional<ShortURL> findById(String id) {
        return jpaRepository.findById(id);
    }
}
