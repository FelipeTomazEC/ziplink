package com.github.felipetomazec.ziplink.repositories;

import java.util.Optional;

public interface FindByIdRepository<T, ID> {
    Optional<T> findById(ID id);
}
