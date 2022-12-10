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
    private final Author AUTHOR = new Author(1L, "Roger Zelazny");
    private final Genre GENRE = new Genre(1L, "New Wave");
    private final Book BOOK = new Book(1L, "God of Light", AUTHOR, GENRE);
    @MockBean
    private BookDao dao;
    @Autowired
    private BookServiceImpl service;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;

    @Test
    void shouldReturnBooksCount() {
        var expected = 2L;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        given(dao.save(any(Book.class))).willReturn(BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        var actual = service.create(BOOK.getName(), BOOK.getAuthor().getId(), BOOK.getGenre().getId());
        assertThat(actual).isEqualTo(BOOK);
    }

    @Test
    void shouldFindAllBooks() {
        given(dao.findAll()).willReturn(List.of(BOOK));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(BOOK);
    }

    @Test
    void shouldFindBookById() {
        var expected = new Book(1L, "book name",
                new Author(2L, "author name"), new Genre(2L, "new genre"));
        given(dao.findById(expected.getId())).willReturn(expected);
        var actual = service.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeBook() {
        given(dao.save(any(Book.class))).willReturn(BOOK);
        given(authorService.findById(AUTHOR.getId())).willReturn(AUTHOR);
        given(genreService.findById(GENRE.getId())).willReturn(GENRE);
        given(dao.findById(GENRE.getId())).willReturn(BOOK);
        var book = service.update(BOOK.getId(), "Super book", AUTHOR.getId(), GENRE.getId());
        assertThat(book).isEqualTo(BOOK);
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
