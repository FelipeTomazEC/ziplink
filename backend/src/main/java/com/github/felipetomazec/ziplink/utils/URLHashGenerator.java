package com.github.felipetomazec.ziplink.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class URLHashGenerator {
    public static String hash(String url, int hashSize) {
        var urlBytes = url.getBytes(StandardCharsets.UTF_8);
        var hash = DigestUtils.md5DigestAsHex(urlBytes);
        int firstPortionSize = hashSize / 2;
        int lastPortionSize = hashSize - firstPortionSize;
        var firstPortion = hash.substring(0, firstPortionSize);
        var lastPortion = hash.substring(hash.length() - lastPortionSize);
        return firstPortion.concat(lastPortion);
    }
}
