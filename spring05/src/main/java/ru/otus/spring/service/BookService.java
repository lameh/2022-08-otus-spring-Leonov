package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    int count();

    Long insert(String name, Long authorId, Long genreId);

    List<Book> getAll();

    Book getById(Long id);

    int update(Long id, String name, Long authorId, Long genreId);

    int delete(Long id);
}
