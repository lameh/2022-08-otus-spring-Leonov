package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    public static final Long TESTING_GENRE_ID = 2L;
    public static final String TESTING_GENRE_NAME = "Fantasy";
    public static final Long DELETING_GENRE_ID = 3L;
    public static final String DELETING_GENRE_NAME = "Science Fiction";


    @Autowired
    private GenreDaoJdbc dao;

    @Test
    void shouldReturnGenresCount() {
        var expected = 2;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewGenre() {
        var genre = new Genre(null, "Fantasy");
        var id = dao.insert(genre);
        var actual = dao.getById(id);
        assertThat(actual).extracting(new String[]{"name"}).containsExactly("Fantasy");
    }

    @Test
    void shouldFindAllGenres() {
        var expectedFirst = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
        var expectedSecond = new Genre(DELETING_GENRE_ID, DELETING_GENRE_NAME);
        var actualList = dao.getAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedFirst, expectedSecond);
    }

    @Test
    void shouldFindGenreById() {
        var expected = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
        var actual = dao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeGenre() {
        var expected = new Genre(TESTING_GENRE_ID, "New Wave");
        dao.update(expected);
        var actual = dao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedGenre() {
        assertThatCode(() -> dao.getById(DELETING_GENRE_ID)).doesNotThrowAnyException();
        dao.delete(DELETING_GENRE_ID);
        assertThatThrownBy(() -> dao.getById(DELETING_GENRE_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
