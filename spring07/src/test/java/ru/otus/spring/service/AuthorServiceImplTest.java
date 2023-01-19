package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @MockBean
    private AuthorRepository repository;
    @Autowired
    private AuthorServiceImpl service;

    @Test
    void shouldReturnAuthorsCount() {
        var expected = 2L;
        given(repository.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewAuthor() {
        var expected = new Author(1L, "Isaac Azimov");
        given(repository.save(any(Author.class))).willReturn(expected);
        var actual = service.create("Isaac Azimov");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindAllAuthors() {
        var expected = new Author(1L, "Michael Moorcock");
        given(repository.findAll()).willReturn(List.of(expected));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindAuthorById() {
        var expected = new Author(1L, "Michael Moorcock");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        var actual = service.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeAuthor() {
        var expected = new Author(1L, "Isaac Azimov");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        given(repository.save(any(Author.class))).willReturn(expected);
        var actual = service.update(expected.getId(), expected.getName());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedAuthor() {
        var expected = new Author(1L, "Isaac Azimov");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        service.delete(1L);
        verify(repository, times(1)).delete(expected);
    }
}
