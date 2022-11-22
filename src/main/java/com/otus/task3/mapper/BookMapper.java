package com.otus.task3.mapper;

import com.otus.task3.model.dto.BookDto;
import com.otus.task3.model.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setDescription(book.getDescription());
        bookDto.setIdAuthor(book.getAuthor().getId());
        return bookDto;
    }

    public Book toBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        return book;
    }

}
