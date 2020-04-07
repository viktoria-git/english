package com.english.controller;

import com.english.service.FileService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UploadControllerTest {

    private MockMvc mockMvc;

    private FileService fileService;
    @Before
    public void setUp(){
        this.fileService = Mockito.mock(FileService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UploadController(fileService)).build();
    }

    @Test
    public void uploadFile() throws Exception {
        File file = new File("src/test/resources/test_with_translate.csv");
        FileInputStream input = new FileInputStream(file);
        MockMultipartFile multipartFile =  new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(multipartFile)
                .param("userId", "1"))
                .andExpect(status().isOk());
        verify(fileService, times(1)).uploadFile(1,multipartFile);
    }
}