package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {
    @MockBean
    private GenreRepository repository;
    @Autowired
    private GenreServiceImpl service;

    @Test
    void shouldReturnGenresCount() {
        var expected = 2L;
        given(repository.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewGenre() {
        var id = 1L;
        var name = "Science Fiction";
        var expected = new Genre(id, name);
        given(repository.save(any(Genre.class))).willReturn(expected);
        var actual = service.create(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindAllGenres() {
        var expected = new Genre(1L, "New Wave");
        given(repository.findAll()).willReturn(List.of(expected));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindGenreById() {
        var expected = new Genre(1L, "New Wave");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        var actual = service.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeGenre() {
        var expected = new Genre(1L, "Science Fiction");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        given(repository.save(any(Genre.class))).willReturn(expected);
        var actual = service.update(expected.getId(), expected.getName());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedGenre() {
        var expected = new Genre(1L, "Science Fiction");
        given(repository.findById(expected.getId())).willReturn(Optional.of(expected));
        service.delete(1L);
        verify(repository, times(1)).delete(expected);
    }
}
