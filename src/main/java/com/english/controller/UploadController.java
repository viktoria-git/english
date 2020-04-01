package com.english.controller;

import com.english.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    private static final String REDIRECT = "redirect:/vocabulary";
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
    private final FileService fileService;

    @Autowired
    public UploadController(FileService service) {
        this.fileService = service;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Fill with data from a file: {}", file.getOriginalFilename());
        fileService.uploadFile(file);
        return REDIRECT;
    }


}
