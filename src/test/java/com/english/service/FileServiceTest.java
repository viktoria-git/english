package com.english.service;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class FileServiceTest {

    private WordService wordService;
    private FileService fileService;

    @Before
    public void setUp() {
        this.wordService = Mockito.mock(WordService.class);
        this.fileService = new FileService(wordService);
    }

    @Test
    public void uploadFileTestWithTranslate() throws IOException {
        MultipartFile multipartFile = getMultipartFile("test_with_translate.csv");
        fileService.uploadFile(1, multipartFile);
        verify(wordService, times(3)).create(anyInt(), anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void uploadFileTestWithoutTranslate() throws IOException {
        MultipartFile multipartFile = getMultipartFile("test_without_translate.csv");
        fileService.uploadFile(1, multipartFile);
        verify(wordService, times(3)).create(anyInt(), anyString(), anyString(), anyString());
    }

    @Test(expected = RuntimeException.class)
    public void uploadFileWithWrongFormatTest() throws IOException {
        MultipartFile multipartFile = getMultipartFile("test_wrong_format.txt");
        fileService.uploadFile(1, multipartFile);
    }

    private MultipartFile getMultipartFile(String filename) throws IOException {
        File file = new File("src/test/resources/" + filename);
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
    }

}