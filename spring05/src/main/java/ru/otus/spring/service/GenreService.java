package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {

    int count();

    Long insert(String name);

    List<Genre> getAll();

    Genre getById(Long id);

    int update(Long id, String name);

    int delete(Long id);
}
