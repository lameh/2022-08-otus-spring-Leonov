package ru.otus.spring.service;

import ru.otus.spring.domain.Commentary;

import java.util.List;

public interface CommentaryService {

    Long count();

    Commentary create(String text, Long bookId);

    List<Commentary> findAll();

    Commentary findById(Long id);

    Commentary update(Long id, String text, Long bookId);

    void delete(Long id);

    List<Commentary> findByBookId(Long id);
}
