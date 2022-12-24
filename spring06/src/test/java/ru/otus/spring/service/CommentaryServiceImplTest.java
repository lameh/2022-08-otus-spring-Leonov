package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.CommentaryDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = CommentaryServiceImpl.class)
public class CommentaryServiceImplTest {
    private final Author AUTHOR = new Author(1L, "Roger Zelazny");
    private final Genre GENRE = new Genre(1L, "New Wave");
    private final Book BOOK = new Book(1L, "God of Light", AUTHOR, GENRE);
    private final Commentary COMMENT = new Commentary(1L, "Bestseller!", BOOK);
    @MockBean
    private BookServiceImpl bookService;
    @MockBean
    private CommentaryDao dao;
    @Autowired
    private CommentaryServiceImpl service;

    @Test
    void shouldReturnCommentariesCount() {
        var expected = 2L;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewCommentaries() {
        given(dao.save(any(Commentary.class))).willReturn(COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        var actual = service.create(COMMENT.getText(), 1L);
        assertThat(actual).isEqualTo(COMMENT);
    }

    @Test
    void shouldFindAllCommentaries() {
        given(dao.findAll()).willReturn(List.of(COMMENT));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(COMMENT);
    }

    @Test
    void shouldFindBookById() {
        given(dao.findById(COMMENT.getId())).willReturn(COMMENT);
        var actual = service.findById(COMMENT.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(COMMENT);
    }

    @Test
    void shouldChangeBook() {
        given(dao.save(any(Commentary.class))).willReturn(COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        given(dao.findById(COMMENT.getId())).willReturn(COMMENT);
        var actual = service.update(COMMENT.getId(), "Amazing!", 1L);
        assertThat(actual).isEqualTo(COMMENT);
    }

    @Test
    void shouldDeleteSpecifiedBook() {
        var id = 1L;
        var expectedCount = 1;
        given(dao.delete(id)).willReturn(expectedCount);
        var actualCount = service.delete(id);
        assertThat(actualCount).isEqualTo(expectedCount);
    }

}
