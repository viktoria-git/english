//package com.english.controller;
//
//import com.english.entity.Word;
//import com.english.entity.WordResponse;
//import com.english.service.LevelService;
//import com.english.service.TopicService;
//import com.english.service.WordService;
//import com.english.util.ValidationUtil;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ControllerTest {
//
//    @Mock
//    WordService wordService;
//    @Mock
//    LevelService levelService;
//    @Mock
//    TopicService topicService;
//
//
//    WordController wordController;
//    ValidationUtil validationUtil;
//
//
//    @Before
//    public void init(){
//        validationUtil = new ValidationUtil(wordService);
//        wordController = new WordController(wordService,topicService,levelService,validationUtil);
//    }
//
//    @Test
//    public void wordAddTest(){
//        Map<String, Object> model = new HashMap<>();
//        String str = wordController.add("name","имя",1,1,model);
//        assertEquals("redirect:/",str);
//        verify(wordService,times(1)).createWordResponseFromWord(eq("name"),eq("имя"),eq(1),eq(1));
//    }
//
//    @Test
//    public void wordAddWithNumericWordTest(){
//        Map<String, Object> model = new HashMap<>();
//        String str = wordController.add("1","имя",1,1,model);
//        assertEquals("error_page",str);
//        assertEquals("Not valid params!",model.get("error"));
//    }
//
//    @Test
//    public void wordAddWithWrongTopicIdTest(){
//        Map<String, Object> model = new HashMap<>();
//        String str = wordController.add("name","имя",0,1,model);
//        assertEquals("error_page",str);
//        assertEquals("Not valid params!",model.get("error"));
//    }
//
//    @Test
//    public void getWordTest(){
//        Map<String, Object> model = new HashMap<>();
//        Word word = new Word();
//        word.setWord("name");
//
//        when(wordService.get("name")).thenThrow(new EmptyResultDataAccessException(0));
//        String str = wordController.find("boss",model);
//        System.out.println(str);
//        assertEquals("error_page",str);
//        assertEquals("Word is not found!",model.get("error"));
//    }
//
//    @Test
//    @SuppressWarnings("unchecked")
//    public void getAllTest(){
//        Map<String, Object> model = new HashMap<>();
//        when(wordService.getAllResponses()).thenReturn(Arrays.asList(new WordResponse(),new WordResponse()));
//        String str = wordController.getAll(model);
//        assertEquals("","index",str);
//        assertEquals(2, ((List<Object>)model.get("words")).size());
//    }
//
//}
