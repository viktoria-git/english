package com.english.service;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    private static final String UPLOADED_FOLDER = "/home/viktoria/projects/english/src/main/resources/";
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    private final WordService wordService;

    @Autowired
    public FileService(WordService service) {
        this.wordService = service;
    }

    public void uploadFile(MultipartFile file) {
        if (Objects.requireNonNull(FilenameUtils.getExtension(file.getOriginalFilename())).equals("cvs")) {
            String filename = file.getOriginalFilename();
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + filename);
                Files.write(path, bytes);
                LOGGER.info("File: {} uploaded successfully", filename);

                fillFromFile(filename);
            } catch (IOException e) {
                LOGGER.info("Could not upload file: {}", file);
            }
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
