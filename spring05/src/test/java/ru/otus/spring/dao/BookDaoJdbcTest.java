package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    public static final Long TESTING_AUTHOR_ID = 2L;
    public static final String TESTING_AUTHOR_NAME = "Roger Zelazny";
    public static final Author TESTING_AUTHOR = new Author(TESTING_AUTHOR_ID, TESTING_AUTHOR_NAME);
    public static final Long TESTING_GENRE_ID = 2L;
    public static final String TESTING_GENRE_NAME = "Fantasy";
    public static final Genre TESTING_GENRE = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
    public static final Long TESTING_BOOK_ID = 3L;
    public static final String TESTING_BOOK_NAME = "Nine princes in Amber";
    public static final Book TESTING_BOOK = new Book(TESTING_BOOK_ID, TESTING_BOOK_NAME, TESTING_AUTHOR, TESTING_GENRE);

    @Autowired
    private BookDaoJdbc dao;

    @Test
    void shouldReturnBooksCount() {
        var expected = 1;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewBook() {
        var book = new Book(null, "book name", TESTING_AUTHOR, TESTING_GENRE);
        var id = dao.insert(book);
        var actual = dao.getById(id);
        assertThat(actual).extracting(new String[]{"name", "author.id", "genre.id"})
                .containsExactly("book name", 2L, 2L);
    }

    @Test
    void shouldFindAllBooks() {
        var actualList = dao.getAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(TESTING_BOOK);
    }

    @Test
    void shouldFindBookById() {
        var actual = dao.getById(TESTING_BOOK.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(TESTING_BOOK);
    }

    @Test
    void shouldChangeBook() {
        var expected = new Book(TESTING_BOOK_ID, "book name", TESTING_AUTHOR, TESTING_GENRE);
        dao.update(expected);
        var actual = dao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedBook() {
        assertThatCode(() -> dao.getById(TESTING_BOOK_ID)).doesNotThrowAnyException();
        dao.delete(TESTING_BOOK_ID);
        assertThatThrownBy(() -> dao.getById(TESTING_BOOK_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
