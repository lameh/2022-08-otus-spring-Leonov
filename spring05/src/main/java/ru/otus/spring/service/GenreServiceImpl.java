package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public int count() {
        return dao.count();
    }

    @Override
    public Long insert(String name) {
        var genre = new Genre(null, name);
        checkIfNullOrEmpty(genre);
        return dao.insert(genre);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public Genre getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public int update(Long id, String name) {
        var genre = new Genre(id, name);
        Optional.ofNullable(id).orElseThrow(() -> new MyException("Id of genre is null"));
        checkIfNullOrEmpty(genre);
        return dao.update(genre);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new MyException("Name of genre is null or empty");
        }
    }
}
