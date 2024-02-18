package com.github.felipetomazec.ziplink.usecases.generateshorturl;

import com.github.felipetomazec.ziplink.config.ShortURLConfig;
import com.github.felipetomazec.ziplink.entities.ShortURL;
import com.github.felipetomazec.ziplink.repositories.ExistsRepository;
import com.github.felipetomazec.ziplink.repositories.SaveRepository;
import com.github.felipetomazec.ziplink.usecases.UseCase;
import com.github.felipetomazec.ziplink.utils.URLHashGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateShortUrlUseCase implements UseCase<GenerateShortUrlInput, GenerateShortUrlOutput> {
    private final ExistsRepository<ShortURL, String> existsRepo;
    private final SaveRepository<ShortURL> saveRepo;
    private final ShortURLConfig config;

    @Override
    public GenerateShortUrlOutput execute(GenerateShortUrlInput input) {
        var hash = URLHashGenerator.hash(input.longUrl(), config.getLength());

        if (!existsRepo.exists(hash)) {
            saveRepo.save(new ShortURL(hash, input.longUrl()));
        }

        var shortUrl = config.getBaseUrl().concat(hash);

        return new GenerateShortUrlOutput(shortUrl);
    }
}
