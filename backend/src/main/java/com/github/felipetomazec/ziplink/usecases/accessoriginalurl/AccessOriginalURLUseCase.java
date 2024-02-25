package com.github.felipetomazec.ziplink.usecases.accessoriginalurl;

import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.FindByIdRepository;
import com.github.felipetomazec.ziplink.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessLongURLUseCase implements UseCase<AccessLongURLInput, AccessLongURLOutput> {

    private final FindByIdRepository<ShortURL, String> repository;

    @Override
    public AccessLongURLOutput execute(AccessLongURLInput input) {
        var shortURL = repository.findById(input.shortUrlCode())
                .orElseThrow(() -> new InvalidShortURLException(input.shortUrlCode()));

        return new AccessLongURLOutput(shortURL.getLongUrl());
    }
}
