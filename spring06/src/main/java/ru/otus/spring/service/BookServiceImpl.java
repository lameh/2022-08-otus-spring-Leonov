package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.MyException;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional
    public Book create(String name, Long authorId, Long genreId) {
        var author = authorService.findById(authorId);
        var genre = genreService.findById(genreId);
        var book = new Book(null, name, author, genre);
        checkIfNullOrEmpty(book);
        return dao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Book update(Long id, String name, Long authorId, Long genreId) {
        var author = authorService.findById(authorId);
        var genre = genreService.findById(genreId);
        var book = findById(id);
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);
        checkIfNullOrEmpty(book);
        return dao.save(book);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
    }

    private void checkIfNullOrEmpty(Book book) {
        if (isEmpty(book.getName())) {
            throw new MyException("Name of book is null or empty");
        }
    }
}
