package com.english.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileUtils {
    private static final String FILE_WORDS = "src/main/resources/files/words";
    private static final String FILE_TRANSLATED_WORDS = "src/main/resources/files/translatedWords";

    public static Map<String, String> getAllWords() throws IOException {
        List<String> strings = Files.lines(Paths.get(FILE_WORDS)).collect(Collectors.toList());
        List<String> translatedWords = Files.lines(Paths.get(FILE_TRANSLATED_WORDS)).collect(Collectors.toList());
        return IntStream.range(0, Math.min(strings.size(), translatedWords.size()))
                .boxed()
                .collect(Collectors.toMap(strings::get, translatedWords::get));
    }
}
