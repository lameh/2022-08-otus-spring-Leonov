package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(CommentaryDaoJpa.class)
public class CommentaryDaoJpaTest {

    private final Author AUTHOR = new Author(3L, "Isaac Azimov");
    private final Genre GENRE = new Genre(3L, "Science Fiction");
    private final Book BOOK = new Book(3L, "Foundation", AUTHOR, GENRE);
    private final Commentary COMMENTARY = new Commentary(2L, "Good!", BOOK);

    @Autowired
    private CommentaryDaoJpa dao;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldReturnCommentariesCount() {
        var expected = 2;
        var actual = dao.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldCreateNewCommentary() {
        var commentary = new Commentary(null, "mysterious book", BOOK);
        var saved = dao.save(commentary);
        var actual = dao.findById(saved.getId());
        assertThat(actual).extracting(new String[]{"text", "book.author.id", "book.genre.id"})
                .containsExactly("mysterious book", 3L, 3L);
    }

    @Test
    void shouldFindAllCommentaries() {
        var actualList = dao.findAll();
        assertEquals(2, actualList.size());
    }

    @Test
    void shouldFindCommentaryById() {
        var actual = dao.findById(COMMENTARY.getId());
        assertEquals(2L, actual.getId());
        assertEquals(COMMENTARY.getText(), actual.getText());
        assertEquals(COMMENTARY.getBook().getId(), actual.getBook().getId());
    }

    @Test
    void shouldChangeCommentary() {
        var expected = dao.findById(COMMENTARY.getId());
        expected.setText("new text");
        dao.save(expected);
        var actual = dao.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedCommentary() {
        var commentary = entityManager.find(Commentary.class, COMMENTARY.getId());
        dao.delete(COMMENTARY.getId());
        entityManager.remove(commentary);
        commentary = entityManager.find(Commentary.class, COMMENTARY.getId());
        assertThat(commentary).isNull();
    }
}
