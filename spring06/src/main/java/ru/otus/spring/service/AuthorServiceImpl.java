package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Author create(String name) {
        Author author = new Author(null, name);
        checkIfNullOrEmpty(author);
        return dao.save(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Author update(Long id, String name) {
        var author = new Author(id, name);
        Optional.ofNullable(id).orElseThrow(() -> new MyException("Author's id is null."));
        checkIfNullOrEmpty(author);
        return dao.save(author);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Author author) {
        if (isEmpty(author.getName())) {
            throw new MyException("Author's name is null or empty");
        }
    }
}
