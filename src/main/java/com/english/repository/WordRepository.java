package com.english.repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called wordRepository
// CRUD refers Create, Read, Update, Delete

import com.english.entity.Word;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word,Integer> {

}
