package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Book;
import ru.otus.spring.service.BookService;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class BookShell {

    private final BookService bookService;

    @ShellMethod(value = "How many books are", key = {"bc", "book_count"})
    public Long count() {
        return bookService.count();
    }

    @ShellMethod(value = "Insert book", key = {"bi", "book_insert"})
    public String create(@ShellOption String name, @ShellOption Long authorId, @ShellOption Long genreId) {
        var book = bookService.create(name, authorId, genreId);
        return book.getId() != null ? "Book was created" : "Book was not created";
    }

    @ShellMethod(value = "Get all books", key = {"bfa", "book_findAll"})
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Get book by id", key = {"bfi", "book_findById"})
    public Book findById(@ShellOption Long id) {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Update book", key = {"bu", "book_update"})
    public String update(@ShellOption Long id, @ShellOption String name,
                         @ShellOption Long authorId, @ShellOption Long genreId) {
        var book = bookService.update(id, name, authorId, genreId);
        return Objects.equals(book.getId(), id) ? "Book was updated" : "Book was not updated";
    }

    @ShellMethod(value = "Book delete", key = {"bd", "book_delete"})
    public String delete(@ShellOption Long id) {
        bookService.delete(id);
        return "Book was deleted";
    }
}
