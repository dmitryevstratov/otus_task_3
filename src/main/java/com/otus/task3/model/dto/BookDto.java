package com.otus.task3.model.dto;

import com.otus.task3.model.entity.Author;

public class BookDto {

    private Long id;

    private String name;

    private String description;

    private Long idAuthor;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", idAuthor=" + idAuthor +
                '}';
    }
}
