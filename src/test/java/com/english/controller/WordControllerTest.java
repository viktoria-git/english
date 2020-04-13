package com.english.controller;

import com.english.entity.WordResponse;
import com.english.utils.TestDataSourceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static com.english.utils.TestHelper.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WordControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private RestTemplate restTemplate;

    private MockMvc mockMvc;
    private MockRestServiceServer mockServer;

    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void getAllTest() throws Exception {
        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allWordResponses.toArray(), result);
    }

    @Test
    public void removeAllTest() throws Exception {
        mockMvc.perform(delete("/removeAll")
                .param("userId", "1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(content().string("[]"));
    }

    @Test
    public void removeTest() throws Exception {
        mockMvc.perform(delete("/remove")
                .param("userId", "1")
                .param("id", "125"))
                .andExpect(status().isOk());

        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertFalse(actual.contains("dog"));
    }

    @Test
    public void createTest() throws Exception {
        URI uri = new URI("http://localhost:3000/translate/clear");
        mockServer.expect(ExpectedCount.once(),
                requestTo(uri))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(EXPECTED_TRANSLATE_CLEAR))
                );

        mockMvc.perform(post("/add")
                .param("userId", "1")
                .param("word", "clear")
                .param("topic", "Other")
                .param("level", "Elementary"))
                .andExpect(status().isOk());

        String actual = mockMvc.perform(get("/vocabulary")
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertTrue(actual.contains("clear"));
    }

    @Test
    public void findIfWordIsPresentTest() throws Exception {
        String actual = mockMvc.perform(get("/find")
                .param("userId", "1")
                .param("searchedWord", "cat"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allWordResponsesWithSearchedWord.toArray(), result);
    }

    @Test
    public void findIfWordIsAbsentTest() throws Exception {
        String actual = mockMvc.perform(get("/find")
                .param("userId", "1")
                .param("searchedWord", "main"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allWordResponses.toArray(), result);
    }

    @Test
    public void orderByWordTest() throws Exception {
        String actual = mockMvc.perform(get("/order")
                .param("userId", "1")
                .param("order", "word"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allSortedWordResponsesByWord.toArray(), result);
    }

    @Test
    public void orderByLevelTest() throws Exception {
        String actual = mockMvc.perform(get("/order")
                .param("userId", "1")
                .param("order", "level_id"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allSortedWordResponsesByLevel.toArray(), result);
    }

    @Test
    public void filterByLevelTest() throws Exception {
        String actual = mockMvc.perform(get("/filter")
                .param("userId", "1")
                .param("topic", "0")
                .param("level", "Pre-Intermediate"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allFilteredWordResponsesByLevel.toArray(), result);
    }

    @Test
    public void filterByTopicTest() throws Exception {
        String actual = mockMvc.perform(get("/filter")
                .param("userId", "1")
                .param("topic", "Other")
                .param("level", "0"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allFilteredWordResponsesByTopic.toArray(), result);
    }

    @Test
    public void filterByTopicAndLevelTest() throws Exception {
        String actual = mockMvc.perform(get("/filter")
                .param("userId", "1")
                .param("topic", "Travel")
                .param("level", "Elementary"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        WordResponse[] result = mapper.readValue(actual, WordResponse[].class);
        Assert.assertArrayEquals(allFilteredWordResponsesByTopicAndLevel.toArray(), result);
    }
}
