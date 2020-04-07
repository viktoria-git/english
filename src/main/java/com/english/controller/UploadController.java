package com.english.controller;

import com.english.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private static final String REDIRECT = "redirect:/vocabulary";
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private final FileService fileService;

    @Autowired
    public UploadController(FileService service) {
        this.fileService = service;
    }

    @PostMapping(value = "/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Fill with data from a file: {}", file.getOriginalFilename());
        fileService.uploadFile(file);
        return REDIRECT;
    }


}
