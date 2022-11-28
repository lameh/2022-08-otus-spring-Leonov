package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    int count();

    Long insert(Author author);

    List<Author> getAll();

    Author getById(Long id);

    int update(Author author);

    int delete(Long id);
}
