package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @MockBean
    private AuthorDao dao;
    @Autowired
    private AuthorServiceImpl service;

    @Test
    void shouldReturnAuthorsCount() {
        var expected = 2;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        var id = 1L;
        var name = "Isaac Azimov";
        given(dao.insert(any(Author.class))).willReturn(id);
        var actual = service.insert(name);
        assertThat(actual).isEqualTo(id);
    }

    @Test
    void shouldFindAllAuthors() {
        var expected = new Author(1L, "Michael Moorcock");
        given(dao.getAll()).willReturn(List.of(expected));
        var actualList = service.getAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindAuthorById() {
        var expected = new Author(1L, "Michael Moorcock");
        given(dao.getById(expected.getId())).willReturn(expected);
        var actual = service.getById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeAuthor() {
        var id = 1L;
        var name = "Isaac Azimov";
        var expectedCount = 1;
        given(dao.update(any(Author.class))).willReturn(expectedCount);
        var actualCount = service.update(id, name);
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    void shouldDeleteSpecifiedAuthor() {
        var id = 1L;
        var expectedCount = 1;
        given(dao.delete(id)).willReturn(expectedCount);
        var actualCount = service.delete(id);
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
