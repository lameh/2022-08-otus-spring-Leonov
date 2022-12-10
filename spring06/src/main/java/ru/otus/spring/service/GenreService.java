package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    Genre create(String name);

    List<Genre> findAll();

    Genre findById(Long id);

    Genre update(Long id, String name);

    int delete(Long id);
}
