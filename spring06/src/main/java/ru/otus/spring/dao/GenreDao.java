package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    Long count();

    Genre save(Genre genre);

    List<Genre> findAll();

    Genre findById(Long id);

    int delete(Long id);
}
