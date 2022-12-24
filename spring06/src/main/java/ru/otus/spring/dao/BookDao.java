package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    Long count();

    Book save(Book book);

    List<Book> findAll();

    Book findById(Long id);

    int delete(Long id);
}
