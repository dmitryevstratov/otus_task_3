package com.otus.task3.service.api;

import com.otus.task3.model.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto save(String name, String description, Long idAuthor, String genre);

    BookDto update(Long id, String name, String description, Long idAuthor, String genre);

    void deleteById(Long id);

    BookDto getById(Long id);

    List<BookDto> findAll();

}
