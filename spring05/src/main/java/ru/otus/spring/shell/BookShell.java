package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(value = "How many books are", key = {"bc", "book_count"})
    public int count() {
        return bookService.count();
    }

    @ShellMethod(value = "Insert book", key = {"bi", "book_insert"})
    public String insert(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        Long id = bookService.insert(name, authorId, genreId);
        return id > 0 ? "Book was created" : "Book was not created";
    }

    @ShellMethod(value = "Get all books", key = {"bga", "book_getAll"})
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Get book by id", key = {"bgi", "book_getById"})
    public Book getById(@ShellOption Long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Update book", key = {"bu", "book_update"})
    public String update(@ShellOption Long id, @ShellOption String name,
                         @ShellOption Long authorId, @ShellOption Long genreId) {
        var res = bookService.update(id, name, authorId, genreId);
        return res == 1 ? "Book was updated" : "Book was not updated";
    }

    @ShellMethod(value = "Book delete", key = {"bd", "book_delete"})
    public String delete(@ShellOption Long id) {
        var res = bookService.delete(id);
        return res == 1 ? "Book was deleted" : "Book was not deleted";
    }
}
