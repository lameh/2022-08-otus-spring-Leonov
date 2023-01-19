package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.MyException;
import ru.otus.spring.repository.GenreRepository;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Genre create(String name) {
        var genre = new Genre(null, name);
        checkIfNullOrEmpty(genre);
        return repository.save(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MyException("Genre not found"));
    }

    @Override
    @Transactional
    public Genre update(Long id, String name) {
        var genre = new Genre(id, name);
        ofNullable(id).orElseThrow(() -> new MyException("Id of genre is null"));
        checkIfNullOrEmpty(genre);
        return repository.save(genre);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Genre genre = repository.findById(id).orElseThrow(() -> new MyException("Author not found"));
        repository.delete(genre);
    }

    private void checkIfNullOrEmpty(Genre genre) {
        if (isEmpty(genre.getName())) {
            throw new MyException("Author's name is null or empty");
        }
    }
}
