package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Commentary;
import ru.otus.spring.service.CommentaryService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class CommentaryShell {

    private final CommentaryService commentaryService;

    @ShellMethod(value = "How many commentary are", key = {"cc", "commentary_count"})
    public Long count() {
        return commentaryService.count();
    }

    @ShellMethod(value = "Insert commentary", key = {"ci", "commentary_insert"})
    public String create(@ShellOption String text, @ShellOption Long bookId) {
        var commentary = commentaryService.create(text, bookId);
        return commentary.getId() != null ? "Commentary was created" : "Commentary was not created";
    }

    @ShellMethod(value = "Get all commentary", key = {"cfa", "commentary_findAll"})
    public List<Commentary> findAll() {
        return commentaryService.findAll();
    }

    @ShellMethod(value = "Get commentary by id", key = {"cfi", "commentary_findById"})
    public Commentary findById(@ShellOption Long id) {
        return commentaryService.findById(id);
    }

    @ShellMethod(value = "Update commentary", key = {"cu", "commentary_update"})
    public String update(@ShellOption Long id, @ShellOption String text,
                         @ShellOption Long bookId) {
        var commentary = commentaryService.update(id, text, bookId);
        return Objects.equals(commentary.getId(), id) ? "Commentary was updated" : "Commentary was not updated";
    }

    @ShellMethod(value = "Commentary delete", key = {"cd", "commentary_delete"})
    public String delete(@ShellOption Long id) {
        commentaryService.delete(id);
        return "Commentary was deleted";
    }

    @ShellMethod(value = "Find commentary of book", key = {"cfb", "commentary_findByBookId"})
    public List<Commentary> findByBookId (@ShellOption Long id) {
        return commentaryService.findByBookId(id);
    }
}
