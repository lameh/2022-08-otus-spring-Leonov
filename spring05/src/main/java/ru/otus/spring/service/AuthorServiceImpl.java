package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.exception.MyException;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Long insert(String name) {
        var author = new Author(null, name);
        checkIfNullOrEmpty(author);
        return dao.insert(author);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public Author getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public int update(Long id, String name) {
        var author = new Author(id, name);
        Optional.ofNullable(id).orElseThrow(() -> new MyException("Author's id is null"));
        checkIfNullOrEmpty(author);
        return dao.update(author);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Author author) {
        if (isEmpty(author.getName())) {
            throw new MyException("Author's name is null or empty");
        }
    }
}
