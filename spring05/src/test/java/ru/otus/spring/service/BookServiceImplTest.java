package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = BookServiceImpl.class)
public class BookServiceImplTest {
    @MockBean
    private BookDao dao;
    @Autowired
    private BookServiceImpl service;

    @Test
    void shouldReturnBooksCount() {
        var expected = 2;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        var authorId = 1L;
        var genreId = 1L;
        var id = 1L;
        var name = "book name";
        given(dao.insert(any(Book.class))).willReturn(id);
        var actual = service.insert(name, authorId, genreId);
        assertThat(actual).isEqualTo(id);
    }

    @Test
    void shouldFindAllBooks() {
        var expected = new Book(1L, "book name",
                new Author(2L, "author name"), new Genre(2L, "new genre"));
        given(dao.getAll()).willReturn(List.of(expected));
        var actualList = service.getAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindBookById() {
        var expected = new Book(1L, "book name",
                new Author(2L, "author name"), new Genre(2L, "new genre"));
        given(dao.getById(expected.getId())).willReturn(expected);
        var actual = service.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeBook() {
        var id = 1L;
        var name = "book name";
        var authorId = 1L;
        var genreId = 1L;
        var expectedCount = 1;
        given(dao.update(any(Book.class))).willReturn(expectedCount);
        var actualCount = service.update(id, name, authorId, genreId);
        assertThat(actualCount).isEqualTo(expectedCount);
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
