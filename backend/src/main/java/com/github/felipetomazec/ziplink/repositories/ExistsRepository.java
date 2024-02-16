package com.github.felipetomazec.ziplink.repositories;

public interface ExistsRepository <T, ID> {
    boolean exists(ID id);
}
