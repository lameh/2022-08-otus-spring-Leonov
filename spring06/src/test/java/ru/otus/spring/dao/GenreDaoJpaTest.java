package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreDaoJpa.class)
public class GenreDaoJpaTest {

    public static final Long TESTING_GENRE_ID = 2L;
    public static final String TESTING_GENRE_NAME = "Fantasy";
    public static final Long DELETING_GENRE_ID = 3L;
    public static final String DELETING_GENRE_NAME = "Science Fiction";

    @Autowired
    private GenreDaoJpa dao;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnGenresCount() {
        var expected = 2;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateNewGenre() {
        var genre = new Genre(null, "Fantasy");
        var saved = dao.save(genre);
        var actual = dao.findById(saved.getId());
        assertThat(actual).extracting(new String[]{"name"}).containsExactly("Fantasy");
    }

    @Test
    void shouldFindAllGenres() {
        var expectedFirst = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
        var expectedSecond = new Genre(DELETING_GENRE_ID, DELETING_GENRE_NAME);
        var actualList = dao.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedFirst, expectedSecond);
    }

    @Test
    void shouldFindGenreById() {
        var expected = new Genre(TESTING_GENRE_ID, TESTING_GENRE_NAME);
        var actual = dao.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeGenre() {
        var expected = new Genre(TESTING_GENRE_ID, "New Wave");
        dao.save(expected);
        var actual = dao.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedGenre() {
        var genre = entityManager.find(Genre.class, DELETING_GENRE_ID);
        dao.delete(DELETING_GENRE_ID);
        entityManager.detach(genre);
        genre = entityManager.find(Genre.class, DELETING_GENRE_ID);
        assertThat(genre).isNull();
    }
}
