package com.otus.task3.service.api;

import com.otus.task3.model.dto.AuthorDto;
import com.otus.task3.model.dto.GenreDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAllAuthor();

}
