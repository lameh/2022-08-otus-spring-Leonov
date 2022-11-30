package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    public static final RowMapper<Book> ROW_MAPPER = new BookMapper();

    private final NamedParameterJdbcOperations npjdbc;

    @Override
    public int count() {
        return npjdbc.queryForObject("select count(*) from book", Collections.emptyMap(), Integer.class);
    }

    @Override
    public Long insert(Book book) {
        var params = new MapSqlParameterSource(Map.of("name", book.getName(), "authorId",
                book.getAuthor().getId(), "genreId", book.getGenre().getId()));
        var keyHolder = new GeneratedKeyHolder();
        npjdbc.update("insert into book (`name`, author_id, genre_id) values (:name, :authorId, :genreId)",
                params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b.id as book_id, b.name as book_name, " +
                "a.id as author_id, a.name as author_name, " +
                "g.id as genre_id, g.name as genre_name" +
                " from book b left join author a on a.id = b.author_id" +
                            " left join genre g on g.id = b.genre_id";
        return npjdbc.query(sql, ROW_MAPPER);
    }

    @Override
    public Book getById(Long id) {
        String sql = "select b.id as book_id, b.name as book_name, "+
                "a.id as author_id, a.name as author_name, " +
                "g.id as genre_id, g.name as genre_name" +
                " from book b left join author a on a.id = b.author_id "+
                            " left join genre g on g.id = b.genre_id where b.id = :id";
        return npjdbc.queryForObject(sql, Map.of("id", id), ROW_MAPPER);
    }

    @Override
    public int update(Book book) {
        return npjdbc.update("update book set `name` = :name, author_id = :authorId, genre_id = :genreId where id = :id",
                Map.of("id", book.getId(), "name", book.getName(), "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public int delete(Long id) {
        return npjdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            var author = new Author(rs.getLong("author_id"), rs.getString("author_name"));
            var genre = new Genre(rs.getLong("genre_id"), rs.getString("genre_name"));
            return new Book(rs.getLong("book_id"), rs.getString("book_name"), author, genre);
        }
    }
}
