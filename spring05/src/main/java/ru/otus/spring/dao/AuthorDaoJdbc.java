package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import org.springframework.jdbc.core.RowMapper;;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor

public class AuthorDaoJdbc implements AuthorDao{

    public static final RowMapper<Author> ROW_MAPPER = new BeanPropertyRowMapper<>(Author.class);

    private final NamedParameterJdbcOperations npjdbc;

    @Override
    public int count() {
        return npjdbc.queryForObject("select count(*) from author", Collections.emptyMap(), Integer.class);
    }

    @Override
    public Long insert(Author author) {
        var params = new MapSqlParameterSource(Map.of("name", author.getName()));
        var keyHolder = new GeneratedKeyHolder();
        npjdbc.update("insert into author (name) values (:name)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Author> getAll() {
        return npjdbc.query("select id, name from author", ROW_MAPPER);
    }

    @Override
    public Author getById(Long id) {
        return npjdbc.queryForObject("select id, name from author where id = :id", Map.of("id", id), ROW_MAPPER);
    }

    @Override
    public int update(Author author) {
        return npjdbc.update("update author set name = :name where id = :id",
                Map.of("id", author.getId(), "name", author.getName()));
    }

    @Override
    public int delete(Long id) {
        return npjdbc.update("delete from author where id = :id", Map.of("id", id));
    }
}
