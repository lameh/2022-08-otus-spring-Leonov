package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    int count();

    Long insert(Genre genre);

    List<Genre> getAll();

    Genre getById(Long id);

    int update(Genre genre);

    int delete(Long id);
}
