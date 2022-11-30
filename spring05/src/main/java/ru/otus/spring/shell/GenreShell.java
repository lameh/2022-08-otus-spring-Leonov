package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class GenreShell {

    private final GenreService genreService;

    @ShellMethod(value = "How many genres are", key = {"gc", "genre_count"})
    public int count() {
        return genreService.count();
    }

    @ShellMethod(value = "Insert genre", key = {"gi", "genre_insert"})
    public String insert(@ShellOption String name) {
        Long id = genreService.insert(name);
        return id > 0 ? "Genre was created" : "Genre was not created";
    }

    @ShellMethod(value = "Get all genres", key = {"gga", "genre_getAll"})
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Get genre by id", key = {"ggi", "genre_getById"})
    public Genre getById(@ShellOption Long id) {
        return genreService.getById(id);
    }

    @ShellMethod(value = "Update genre", key = {"gu", "genre_update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        var res = genreService.update(id, name);
        return res == 1 ? "Genre was updated" : "Genre was not updated";
    }

    @ShellMethod(value = "Genre delete", key = {"gd", "genre_delete"})
    public String delete(@ShellOption Long id) {
        var res = genreService.delete(id);
        return res == 1 ? "Genre was deleted" : "Genre was not deleted";
    }
}
