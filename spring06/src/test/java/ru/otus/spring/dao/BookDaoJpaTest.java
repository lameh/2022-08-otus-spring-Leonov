package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookDaoJpa.class)
public class BookDaoJpaTest {

    public static final Long TESTING_AUTHOR_ID = 3L;
    public static final String TESTING_AUTHOR_NAME = "Isaac Azimov";
    public static final Author TESTING_AUTHOR = new Author(TESTING_AUTHOR_ID, TESTING_AUTHOR_NAME);
    public static final Long TESTING_GENRE_ID = 3L;
    public static final String TESTING_GENRE_NAME = "Science Fiction";
    public static final Genre TESTING_GENRE = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
    public static final Long TESTING_BOOK_ID = 3L;
    public static final String TESTING_BOOK_NAME = "Foundation";
    public static final Book TESTING_BOOK = new Book(TESTING_BOOK_ID, TESTING_BOOK_NAME, TESTING_AUTHOR, TESTING_GENRE);

    @Autowired
    private BookDaoJpa dao;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnBooksCount() {
        var expected = 1;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateNewBook() {
        var book = new Book(null, "book name", TESTING_AUTHOR, TESTING_GENRE);
        var saved = dao.save(book);
        var actual = dao.findById(saved.getId());
        assertThat(actual).extracting(new String[]{"name", "author.id", "genre.id"})
                .containsExactly("book name", 3L, 3L);
    }

    @Test
    void shouldFindAllBooks() {
        var actualList = dao.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(TESTING_BOOK);
    }

    @Test
    void shouldFindBookById() {
        var actual = dao.findById(TESTING_BOOK.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(TESTING_BOOK);
    }

    @Test
    void shouldChangeBook() {
        var expected = new Book(TESTING_BOOK_ID, "book name", TESTING_AUTHOR, TESTING_GENRE);
        dao.save(expected);
        var actual = dao.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedBook() {
        var book = entityManager.find(Book.class, TESTING_BOOK_ID);
        dao.delete(TESTING_BOOK_ID);
        entityManager.detach(book);
        book = entityManager.find(Book.class, TESTING_BOOK_ID);
        assertThat(book).isNull();
    }
}
