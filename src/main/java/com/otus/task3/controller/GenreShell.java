package com.otus.task3.controller;

import com.otus.task3.model.dto.GenreDto;
import com.otus.task3.service.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class GenreShell {

    private final GenreService genreService;

    @Autowired
    public GenreShell(GenreService genreService) {
        this.genreService = genreService;
    }

    @ShellMethod(key = "findAll-genre", value = "Genre find all")
    public List<GenreDto> findAllGenre() {
        return genreService.getAllGenre();
    }

}
