package com.english.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final WordService wordService;

    @Autowired
    public FileService(WordService service) {
        this.wordService = service;
    }

    public void uploadFile(Integer userId, MultipartFile file) {
        if (Objects.requireNonNull(file.getOriginalFilename()).contains("csv")) {
            LOGGER.info("File: {} uploaded successfully", file.getOriginalFilename());
            fillFromFile(userId, file);
        } else {
            LOGGER.info("Wrong file: {} format!", file);
            throw new RuntimeException("Wrong file format!");
        }
    }

    private void fillFromFile(String filename) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(UPLOADED_FOLDER + filename));
        List<String> words = Objects.requireNonNull(lines).collect(Collectors.toList());
        for (String line : words) {
            String[] s = line.split(",");
            if (s.length == 3) {
                wordService.create(s[0], s[1], s[2]);
            } else {
                wordService.create(s[0], s[1], s[2], s[3]);
            }
        }
    }
}
