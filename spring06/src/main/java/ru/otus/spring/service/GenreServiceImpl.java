package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.MyException;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    
    private final GenreDao dao;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre create(String name) {
        Genre genre = new Genre(null, name);
        checkIfNullOrEmpty(genre);
        return dao.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Genre update(Long id, String name) {
        var genre = new Genre(id, name);
        Optional.ofNullable(id).orElseThrow(() -> new MyException("Genre's id is null."));
        checkIfNullOrEmpty(genre);
        return dao.save(genre);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new MyException("Genre's name is null or empty");
        }
    }
}
