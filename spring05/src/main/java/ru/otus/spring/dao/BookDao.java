package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int count();

    Long insert(Book book);

    List<Book> getAll();

    Book getById(Long id);

    int update(Book book);

    int delete(Long id);
}
