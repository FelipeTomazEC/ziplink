package com.github.felipetomazec.ziplink.usecases.generateshorturl;

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
    public static final int HASH_SIZE = 12;

    private final ExistsRepository<ShortURL, String> existsRepo;
    private final SaveRepository<ShortURL> saveRepo;

    @Override
    public GenerateShortUrlOutput execute(GenerateShortUrlInput input) {
        var shortUrl = URLHashGenerator.hash(input.longUrl(), HASH_SIZE);

        if (!existsRepo.exists(shortUrl)) {
            saveRepo.save(new ShortURL(shortUrl, input.longUrl()));
        }

        return new GenerateShortUrlOutput(shortUrl);
    }
}
