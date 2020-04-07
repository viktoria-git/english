package com.english.controller;

import com.english.service.LevelService;
import com.english.service.MyUserDetailsService;
import com.english.service.TopicService;
import com.english.service.WordService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static com.english.TestHelper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WordControllerTest {

    private MockMvc mockMvc;

    private WordService wordService;
    private TopicService topicService;
    private LevelService levelService;
    private MyUserDetailsService userService;

    @Before
    public void setUp() throws Exception {
        this.wordService = Mockito.mock(WordService.class);
        this.topicService = Mockito.mock(TopicService.class);
        this.levelService = Mockito.mock(LevelService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new WordController(wordService, topicService, levelService)).build();

    }

    @Test
    public void getAllTest() throws Exception {
        when(wordService.getAllWordResponses()).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        mockMvc.perform(get("/vocabulary")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("topics", topicList))
                .andExpect(model().attribute("levels", levelList));
    }

    @Test
    public void addTest() throws Exception {
        mockMvc.perform(post("/add")
                .param("word","cat")
                .param("translate", "кот")
                .param("topic", "Other")
                .param("level", "Elementary")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/vocabulary"));
    }

    @Test
    public void removeTest() throws Exception {
        mockMvc.perform(get("/remove")
                .param("id","1")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/vocabulary"));
    }

    @Test
    public void removeAllTest() throws Exception {
        mockMvc.perform(get("/removeAll")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/vocabulary"));
    }

    @Test
    public void orderTest() throws Exception {
        when(wordService.order("translate")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        mockMvc.perform(get("/order")
                .param("order", "translate")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("topics", topicList))
                .andExpect(model().attribute("levels", levelList));
    }

    @Test
    public void filterTest() throws Exception {
        when(wordService.filter("Travel","Elementary")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        mockMvc.perform(get("/filter")
                .param("topic", "Travel")
                .param("level", "Elementary")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("topics", topicList))
                .andExpect(model().attribute("levels", levelList));
    }

    @Test
    public void findTest() throws Exception {
        when(wordService.find("cat")).thenReturn(wordResponses);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        mockMvc.perform(get("/find")
                .param("searchedWord","cat")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("topics", topicList))
                .andExpect(model().attribute("levels", levelList));
        verify(wordService, times(0)).getAllWordResponses();
    }

    @Test
    public void findWhenWordIsAbsentTest() throws Exception {
        when(wordService.find("cat")).thenReturn(null);
        when(topicService.getAll()).thenReturn(topicList);
        when(levelService.getAll()).thenReturn(levelList);
        mockMvc.perform(get("/find")
                .param("searchedWord","cat")
                .with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("topics", topicList))
                .andExpect(model().attribute("levels", levelList));
        verify(wordService, times(1)).getAllWordResponses();
    }
}