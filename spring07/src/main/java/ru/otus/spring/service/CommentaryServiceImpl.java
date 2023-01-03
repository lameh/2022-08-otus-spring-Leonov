package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.exception.MyException;
import ru.otus.spring.repository.CommentaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository repository;
    private final BookService bookService;
    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Commentary create(String text, Long bookId) {
        var book = bookService.findById(bookId);
        var commentary = new Commentary(null, text, book);
        return repository.save(commentary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Commentary> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Commentary findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new MyException("Commentary is empty"));
    }

    @Override
    @Transactional
    public Commentary update(Long id, String text, Long bookId) {
        var book = bookService.findById(bookId);
        var commentary = findById(id);
        commentary.setBook(book);
        commentary.setText(text);
        return repository.save(commentary);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var commentary = findById(id);
        repository.delete(commentary);
    }

    @Override
    public List<Commentary> findByBookId(Long id) {
        var book = bookService.findById(id);
        return book.getCommentary();
    }
}
