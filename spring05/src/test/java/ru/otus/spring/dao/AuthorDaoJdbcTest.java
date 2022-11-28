package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    public static final Long TESTING_AUTHOR_ID = 2L;
    public static final String TESTING_AUTHOR_NAME = "Roger Zelazny";
    public static final Long DELETING_AUTHOR_ID = 3L;
    public static final String DELETING_AUTHOR_NAME = "Michael Moorcock";


    @Autowired
    private AuthorDaoJdbc dao;

    @Test
    void shouldReturnAuthorsCount() {
        var expected = 2;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        var author = new Author(null, "Isaac Azimov");
        var id = dao.insert(author);
        var actual = dao.getById(id);
        assertThat(actual).extracting(new String[]{"name"}).containsExactly("Isaac Azimov");
    }

    @Test
    void shouldFindAllAuthors() {
        var expectedFirst = new Author(TESTING_AUTHOR_ID, TESTING_AUTHOR_NAME);
        var expectedSecond = new Author(DELETING_AUTHOR_ID, DELETING_AUTHOR_NAME);
        var actualList = dao.getAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(expectedFirst, expectedSecond);
    }

    @Test
    void shouldFindAuthorById() {
        var expected = new Author(TESTING_AUTHOR_ID, TESTING_AUTHOR_NAME);
        var actual = dao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeAuthor() {
        var expected = new Author(TESTING_AUTHOR_ID, "Isaac Azimov");
        dao.update(expected);
        var actual = dao.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedAuthor() {
        assertThatCode(() -> dao.getById(DELETING_AUTHOR_ID)).doesNotThrowAnyException();
        dao.delete(DELETING_AUTHOR_ID);
        assertThatThrownBy(() -> dao.getById(DELETING_AUTHOR_ID))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

}
