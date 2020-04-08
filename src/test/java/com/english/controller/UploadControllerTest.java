package com.english.controller;

import com.english.entity.WordResponse;
import com.english.utils.TestDataSourceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

import static com.english.utils.TestHelper.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UploadControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private RestTemplate restTemplate;

    private MockMvc mockMvc;
    private MockRestServiceServer mockServer;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void uploadFileWithoutTranslate() throws Exception {
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:3000/translate/rain")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString("дождь / литься"))
                );

        File file = new File("src/test/resources/test_without_translate.csv");
        FileInputStream input = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(multipartFile)
                .param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allWordResponsesAddedFromFileWithoutTranslate.toArray(), result);
    }

    @Test
    public void uploadFileWithTranslate() throws Exception {
        File file = new File("src/test/resources/test_with_translate.csv");
        FileInputStream input = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(multipartFile)
                .param("userId", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allWordResponsesAddedFromFileWithTranslate.toArray(), result);
    }
}