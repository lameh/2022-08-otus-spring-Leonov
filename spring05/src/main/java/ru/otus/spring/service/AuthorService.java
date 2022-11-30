package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    int count();

    Long insert(String name);

    List<Author> getAll();

    Author getById(Long id);

    int update(Long id, String name);

    int delete(Long id);
}
