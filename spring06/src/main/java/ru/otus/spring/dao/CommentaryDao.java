package ru.otus.spring.dao;

import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryDao {

    Long count();

    Commentary save(Commentary commentary);

    List<Commentary> findAll();

    Commentary findById(Long id);

    int delete(Long id);
}
