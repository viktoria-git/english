package com.english.controller;

import com.english.entity.Level;
import com.english.service.LevelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.english.TestHelper.levelList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LevelControllerTest {

    private MockMvc mockMvc;

    private LevelService levelService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.levelService = Mockito.mock(LevelService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LevelController(levelService)).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void getAll() throws Exception {
        when(levelService.getAll()).thenReturn(levelList);
        String actual = mockMvc.perform(get("/levels"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Level[] result = objectMapper.readValue(actual, Level[].class);

        Assert.assertEquals(1, result.length);
        Assert.assertEquals(result[0].getLevelName(), "Elementary");
    }
}