package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;

    @ShellMethod(value = "How many authors are", key = {"ac", "author_count"})
    public Long count() {
        return authorService.count();
    }

    @ShellMethod(value = "Insert author", key = {"ai", "author_insert"})
    public String create(@ShellOption String name) {
        var author = authorService.create(name);
        return author.getId() != null ? "Author was created" : "Author was not created";
    }

    @ShellMethod(value = "Get all authors", key = {"afa", "author_findAll"})
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @ShellMethod(value = "Get author by id", key = {"afi", "author_findById"})
    public Author findById(@ShellOption Long id) {
        return authorService.findById(id);
    }

    @ShellMethod(value = "Update author", key = {"au", "author_update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        var author = authorService.update(id, name);
        return Objects.equals(author.getId(), id) ? "Author was updated" : "Author was not updated";
    }

    @ShellMethod(value = "Author delete", key = {"ad", "author_delete"})
    public String delete(@ShellOption Long id) {
        authorService.delete(id);
        return "Author was deleted";
    }
}
