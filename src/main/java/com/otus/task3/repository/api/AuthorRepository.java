package com.otus.task3.repository.api;

import com.otus.task3.model.entity.Author;

import java.util.List;

public interface AuthorRepository {

    List<Author> findAll();

    Author findById(Long id);

}
