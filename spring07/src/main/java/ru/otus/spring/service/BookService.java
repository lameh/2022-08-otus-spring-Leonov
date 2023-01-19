package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    Long count();

    Book create(String name, Long authorId, Long genreId);

    List<Book> findAll();

    Book findById(Long id);

    Book update(Long id, String name, Long authorId, Long genreId);

    void delete(Long id);
}
