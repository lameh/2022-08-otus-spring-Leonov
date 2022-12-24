package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        var expected = 2L;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        var expected = new Author(1L, "Isaac Azimov");
        given(dao.save(any(Author.class))).willReturn(expected);
        var actual = service.create("Isaac Azimov");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindAllAuthors() {
        var expected = new Author(1L, "Michael Moorcock");
        given(dao.findAll()).willReturn(List.of(expected));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindAuthorById() {
        var expected = new Author(1L, "Michael Moorcock");
        given(dao.findById(expected.getId())).willReturn(expected);
        var actual = service.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeAuthor() {
        var expected = new Author(1L, "Isaac Azimov");
        given(dao.findById(expected.getId())).willReturn(expected);
        given(dao.save(any(Author.class))).willReturn(expected);
        var actual = service.update(expected.getId(), expected.getName());
        assertThat(actual).isEqualTo(expected);
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
