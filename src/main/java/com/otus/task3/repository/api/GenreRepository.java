package com.otus.task3.repository.api;

import com.otus.task3.model.entity.Genre;

import java.util.List;

public interface GenreRepository {

    List<Genre> findAll();

}
