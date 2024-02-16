package com.github.felipetomazec.ziplink.usecases;

public interface UseCase<Input, Output>{
    Output execute(Input input);
}
