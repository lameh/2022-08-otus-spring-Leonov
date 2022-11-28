package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.exception.MyException;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public Long insert(String name, Long authorId, Long genreId) {
        var book = new Book(null, name, new Author(authorId, null), new Genre(genreId, null));
        checkIfNullOrEmpty(book);
        return dao.insert(book);
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public Book getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public int update(Long id, String name, Long authorId, Long genreId) {
        var book = new Book(id, name, new Author(authorId, null), new Genre(genreId, null));
        Optional.ofNullable(id).orElseThrow(() -> new MyException("Id of book is null"));
        checkIfNullOrEmpty(book);
        return dao.update(book);
    }

    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Book book) {
        if (isEmpty(book.getName())) {
            throw new MyException("Name of book is null or empty");
        }
    }
}
