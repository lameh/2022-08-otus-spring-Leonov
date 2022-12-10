package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService genreService;

    @ShellMethod(value = "How many genres are", key = {"gc", "genre_count"})
    public Long count() {
        return genreService.count();
    }

    @ShellMethod(value = "Insert genre", key = {"gi", "genre_insert"})
    public String create(@ShellOption String name) {
        var genre = genreService.create(name);
        return genre.getId() != null ? "Genre was created" : "Genre was not created";
    }

    @ShellMethod(value = "Get all genres", key = {"gfa", "genre_findAll"})
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @ShellMethod(value = "Get genre by id", key = {"gfi", "genre_findById"})
    public Genre findById(@ShellOption Long id) {
        return genreService.findById(id);
    }

    @ShellMethod(value = "Update genre", key = {"gu", "genre_update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        var genre = genreService.update(id, name);
        return Objects.equals(genre.getId(), id) ? "Genre was updated" : "Genre was not updated";
    }

    @ShellMethod(value = "Genre delete", key = {"gd", "genre_delete"})
    public String delete(@ShellOption Long id) {
        var res = genreService.delete(id);
        return res == 1 ? "Genre was deleted" : "Genre was not deleted";
    }
}
