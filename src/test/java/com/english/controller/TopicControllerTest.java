package com.english.controller;

import com.english.entity.Topic;
import com.english.utils.TestDataSourceConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.english.utils.TestHelper.topicList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class})
@WebAppConfiguration
@Sql(scripts = "classpath:test.sql", config = @SqlConfig(encoding = "UTF-8"))
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TopicControllerTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getAll() throws Exception {
        String actual = mockMvc.perform(get("/topics"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Topic[] result = mapper.readValue(actual, Topic[].class);
        Assert.assertArrayEquals(topicList.toArray(), result);
    }
}