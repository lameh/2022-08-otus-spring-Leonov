package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    Author create(String name);

    List<Author> findAll();

    Author findById(Long id);

    Author update(Long id, String name);

    int delete(Long id);
}
