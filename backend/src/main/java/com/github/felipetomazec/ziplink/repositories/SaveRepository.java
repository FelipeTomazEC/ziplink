package com.github.felipetomazec.ziplink.repositories;

public interface SaveRepository<T> {
    void save(T entity);
}
