package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.MyException;
import ru.otus.spring.repository.AuthorRepository;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;
    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Author create(String name) {
        var author = new Author(null, name);
        checkIfNullOrEmpty(author);
        return repository.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MyException("Author not found"));
    }

    @Override
    @Transactional
    public Author update(Long id, String name) {
        var author = new Author(id, name);
        ofNullable(id).orElseThrow(() -> new MyException("Author's id is null"));
        checkIfNullOrEmpty(author);
        return repository.save(author);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Author author = repository.findById(id).orElseThrow(() -> new MyException("Author not found"));
        repository.delete(author);
    }

    private void checkIfNullOrEmpty(Author author) {
        if (isEmpty(author.getName())) {
            throw new MyException("Author's name is null or empty");
        }
    }
}
