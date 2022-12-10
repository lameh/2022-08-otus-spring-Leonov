package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {
    Long count();

    Author save(Author author);

    List<Author> findAll();

    Author findById(Long id);

    int delete(Long id);
}
