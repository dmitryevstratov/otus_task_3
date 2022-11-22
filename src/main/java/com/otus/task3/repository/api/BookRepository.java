package com.otus.task3.repository.api;

import com.otus.task3.model.entity.Book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();

    Book findById(Long id);

    void deleteById(Long id);

    Book save(String name, String description, Long idAuthor, String genre);

    Book update(Long id, String name, String description, Long idAuthor, String genre);
}
