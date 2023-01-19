package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.MyException;
import ru.otus.spring.repository.BookRepository;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final GenreService genreService;
    private final AuthorService authorService;
    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Book create(String name, Long authorId, Long genreId) {
        var author = authorService.findById(authorId);
        var genre = genreService.findById(genreId);
        var book = new Book(null, name, author, genre);
        checkIfNullOrEmpty(book);
        return repository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MyException("Book not found"));
    }

    @Override
    public Book update(Long id, String name, Long authorId, Long genreId) {
        var author = authorService.findById(authorId);
        var genre = genreService.findById(genreId);
        var book = repository.findById(id).orElseThrow(() -> new MyException("Book not found"));
        book.setAuthor(author);
        book.setGenre(genre);
        book.setName(name);
        checkIfNullOrEmpty(book);
        return repository.save(book);
    }

    @Override
    public void delete(Long id) {
        var book = repository.findById(id).orElseThrow(() -> new MyException("Book not found"));
        repository.delete(book);
    }

    private void checkIfNullOrEmpty(Book book) {
        if (isEmpty(book.getName())) {
            throw new MyException("Name of book is null or empty");
        }
        if (book.getAuthor() == null) {
            throw new MyException("Author is null or empty");
        }
        if (book.getGenre() == null) {
            throw new MyException("Genre is null or empty");
        }
    }
}
