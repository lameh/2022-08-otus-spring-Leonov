package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.CommentaryRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = CommentaryServiceImpl.class)
public class CommentaryServiceImplTest {
    private final Author AUTHOR = new Author(1L, "Roger Zelazny");
    private final Genre GENRE = new Genre(1L, "New Wave");
    private final Book BOOK = new Book(1L, "God of Light", AUTHOR, GENRE);
    private final Commentary COMMENT = new Commentary(1L, "Bestseller!", BOOK);
    @MockBean
    private BookServiceImpl bookService;
    @MockBean
    private CommentaryRepository repository;
    @Autowired
    private CommentaryServiceImpl service;

    @Test
    void shouldReturnCommentariesCount() {
        var expected = 2L;
        given(repository.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewCommentaries() {
        given(repository.save(any(Commentary.class))).willReturn(COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        var actual = service.create(COMMENT.getText(), 1L);
        assertThat(actual).isEqualTo(COMMENT);
    }

    @Test
    void shouldFindAllCommentaries() {
        given(repository.findAll()).willReturn(List.of(COMMENT));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(COMMENT);
    }

    @Test
    void shouldFindBookById() {
        given(repository.findById(COMMENT.getId())).willReturn(Optional.of(COMMENT));
        var actual = service.findById(COMMENT.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(COMMENT);
    }

    @Test
    void shouldChangeBook() {
        given(repository.save(any(Commentary.class))).willReturn(COMMENT);
        given(bookService.findById(BOOK.getId())).willReturn(BOOK);
        given(repository.findById(COMMENT.getId())).willReturn(Optional.of(COMMENT));
        var actual = service.update(COMMENT.getId(), "Amazing!", 1L);
        assertThat(actual).isEqualTo(COMMENT);
    }

    @Test
    void shouldDeleteSpecifiedBook() {
        var id = 1L;
        var expectedCount = 1;
        given(repository.findById(COMMENT.getId())).willReturn(Optional.of(COMMENT));
        service.delete(id);
        verify(repository, times(expectedCount)).delete(COMMENT);
    }
}
