//package com.english.util;
//import com.english.service.WordService;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ValidationUtil {
//    private static Logger log = LoggerFactory.getLogger(ValidationUtil.class);
//
//    private WordService wordService;
//
//    @Autowired
//    public ValidationUtil(WordService wordService) {
//        this.wordService = wordService;
//    }
//
//    public boolean checkIsNotFound(String w) {
//        try {
//            if(checkString(w)) {
//                return wordService.get(w) != null;
//            } else return false;
//        }catch (EmptyResultDataAccessException e){
//            return false;
//        }
//    }
//
//    public boolean checkString(String s){
//        return !StringUtils.isNumeric(s);
//    }
//
//    public  boolean checkIsValidParamsForAdd(String word, String translate, Integer id1, Integer id2) {
//        boolean checkWord = checkString(word);
//        boolean checkTranslate = checkString(translate);
//        boolean checkId1 = (id1 != 0);
//        boolean checkId2 = (id2 != 0);
//        return checkWord && checkTranslate && checkId1 && checkId2;
//    }
//}
