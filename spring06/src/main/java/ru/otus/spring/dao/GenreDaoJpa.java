package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoJpa implements GenreDao {

    private final EntityManager entityManager;

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(id) from Genre", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == null) {
            entityManager.persist(genre);
            return genre;
        } else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g ", Genre.class);
        return query.getResultList();
    }

    @Override
    public Genre findById(Long id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public int delete(Long id) {
        var query = entityManager.createQuery("delete from Genre g where g.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
