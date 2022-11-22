package com.otus.task3.service;

import com.otus.task3.mapper.BookMapper;
import com.otus.task3.model.dto.BookDto;
import com.otus.task3.repository.api.AuthorRepository;
import com.otus.task3.repository.api.BookRepository;
import com.otus.task3.repository.api.GenreRepository;
import com.otus.task3.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    @Override
    public BookDto save(String name, String description, Long idAuthor, String genre) {
        return bookMapper.toBookDto(bookRepository.save(name, description, idAuthor, genre));
    }

    @Transactional
    @Override
    public BookDto update(Long id, String name, String description, Long idAuthor, String genre) {
        return bookMapper.toBookDto(bookRepository.update(id, name, description, idAuthor, genre));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public BookDto getById(Long id) {
        return bookMapper.toBookDto(bookRepository.findById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .collect(Collectors.toList());
    }
}
