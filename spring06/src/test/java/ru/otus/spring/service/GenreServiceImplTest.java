package ru.otus.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {
    @MockBean
    private GenreDao dao;
    @Autowired
    private GenreServiceImpl service;

    @Test
    void shouldReturnGenresCount() {
        var expected = 2L;
        given(dao.count()).willReturn(expected);
        var actual = service.count();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldInsertNewGenre() {
        var id = 1L;
        var name = "Science Fiction";
        var expected = new Genre(id, name);
        given(dao.save(any(Genre.class))).willReturn(expected);
        var actual = service.create(name);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldFindAllGenres() {
        var expected = new Genre(1L, "New Wave");
        given(dao.findAll()).willReturn(List.of(expected));
        var actualList = service.findAll();
        assertThat(actualList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(expected);
    }

    @Test
    void shouldFindGenreById() {
        var expected = new Genre(1L, "New Wave");
        given(dao.findById(expected.getId())).willReturn(expected);
        var actual = service.findById(expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void shouldChangeGenre() {
        var expected = new Genre(1L, "Science Fiction");
        given(dao.findById(expected.getId())).willReturn(expected);
        given(dao.save(any(Genre.class))).willReturn(expected);
        var actual = service.update(expected.getId(), expected.getName());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldDeleteSpecifiedGenre() {
        var id = 1L;
        var expectedCount = 1;
        given(dao.delete(id)).willReturn(expectedCount);
        var actualCount = service.delete(id);
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
