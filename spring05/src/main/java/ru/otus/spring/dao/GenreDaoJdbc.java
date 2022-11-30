package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao{

    public static final RowMapper<Genre> ROW_MAPPER = new BeanPropertyRowMapper<>(Genre.class);

    private final NamedParameterJdbcOperations npjdbc;

    @Override
    public int count() {
        return npjdbc.queryForObject("select count(*) from genre", Collections.emptyMap(), Integer.class);
    }

    @Override
    public Long insert(Genre genre) {
        var params = new MapSqlParameterSource(Map.of("name", genre.getName()));
        var keyHolder = new GeneratedKeyHolder();
        npjdbc.update("insert into genre (name) values (:name)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Genre> getAll() {
        return npjdbc.query("select id, name from genre", ROW_MAPPER);
    }

    @Override
    public Genre getById(Long id) {
        return npjdbc.queryForObject("select id, name from genre where id = :id", Map.of("id", id), ROW_MAPPER);
    }

    @Override
    public int update(Genre genre) {
        return npjdbc.update("update genre set name = :name where id = :id",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public int delete(Long id) {
        return npjdbc.update("delete from genre where id = :id", Map.of("id", id));
    }
}
