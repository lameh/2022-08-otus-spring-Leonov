package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    public static final Long TESTING_AUTHOR_ID = 2L;
    public static final String TESTING_AUTHOR_NAME = "H.L. Oldi";
    public static final Long DELETING_AUTHOR_ID = 2L;

    @Autowired
    private AuthorDaoJpa dao;

    @Test
    void shouldReturnAuthorsCount() {
        var expected = 2;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateNewAuthor() {
        var author = new Author(null, "Isaac Azimov");
        var saved = dao.save(author);
        var actual = dao.findById(saved.getId());
        assertThat(actual).extracting(new String[]{"name"}).containsExactly("Isaac Azimov");
    }

    @Test
    void shouldFindAllAuthors() {
        var actualList = dao.findAll();
        assertThat(actualList).hasSize(2);
    }

    @Test
    void shouldFindAuthorById() {
        var expected = new Author(TESTING_AUTHOR_ID, TESTING_AUTHOR_NAME);
        var actual = dao.findById(expected.getId());
        assertThat(actual).extracting(new String[]{"name"}).containsExactly(TESTING_AUTHOR_NAME);
    }

    @Test
    void shouldChangeAuthor() {
        var expected = new Author(TESTING_AUTHOR_ID, "Isaac Azimov");
        dao.save(expected);
        var actual = dao.findById(expected.getId());
        assertThat(actual).extracting(new String[]{"name"}).containsExactly("Isaac Azimov");
    }

    @Test
    void shouldDeleteSpecifiedAuthor() {
        var res = dao.delete(DELETING_AUTHOR_ID);
        assertThat(res).isEqualTo(1);
    }

}
