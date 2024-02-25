package com.github.felipetomazec.ziplink.usecases.accesslongurl;

import com.github.felipetomazec.ziplink.config.ShortURLConfig;
import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.FindByIdRepository;
import com.github.felipetomazec.ziplink.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessLongURLUseCase implements UseCase<AccessLongURLInput, AccessLongURLOutput> {

    private final ShortURLConfig config;
    private final FindByIdRepository<ShortURL, String> repository;

    @Override
    public AccessLongURLOutput execute(AccessLongURLInput input) {
        var shortUrlCode = input.shortUrl().replace(config.getBaseUrl(), "");
        var shortURL = repository.findById(shortUrlCode)
                .orElseThrow(() -> new InvalidShortURLException(input.shortUrl()));

        return new AccessLongURLOutput(shortURL.getLongUrl());
    }
}
