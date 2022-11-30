package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShell {

    private final AuthorService authorService;

    @ShellMethod(value = "How many authors are", key = {"ac", "author_count"})
    public int count() {
        return authorService.count();
    }

    @ShellMethod(value = "Insert author", key = {"ai", "author_insert"})
    public String insert(@ShellOption String name) {
        Long id = authorService.insert(name);
        return id > 0 ? "Author was created" : "Author was not created";
    }

    @ShellMethod(value = "Get all authors", key = {"aga", "author_getAll"})
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Get author by id", key = {"agi", "author_getById"})
    public Author getById(@ShellOption Long id) {
        return authorService.getById(id);
    }

    @ShellMethod(value = "Update author", key = {"au", "author_update"})
    public String update(@ShellOption Long id, @ShellOption String name) {
        var res = authorService.update(id, name);
        return res == 1 ? "Author was updated" : "Author was not updated";
    }

    @ShellMethod(value = "Author delete", key = {"ad", "author_delete"})
    public String delete(@ShellOption Long id) {
        var res = authorService.delete(id);
        return res == 1 ? "Author was deleted" : "Author was not deleted";
    }
}
