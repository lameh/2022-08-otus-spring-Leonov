package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.CommentaryDao;
import ru.otus.spring.domain.Commentary;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryDao dao;
    private final BookService service;

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return dao.count();
    }

    @Override
    @Transactional
    public Commentary create(String text, Long bookId) {
        var book = service.findById(bookId);
        var commentary = new Commentary(null, text, book);
        return dao.save(commentary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Commentary> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Commentary findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Commentary update(Long id, String text, Long bookId) {
        var book = service.findById(bookId);
        var commentary = findById(id);
        commentary.setBook(book);
        commentary.setText(text);
        return dao.save(commentary);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return dao.delete(id);
    }
}
