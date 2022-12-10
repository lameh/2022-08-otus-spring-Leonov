package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CommentaryDaoJpa implements CommentaryDao {

    private final EntityManager entityManager;

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(id) from Commentary", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Commentary save(Commentary commentary) {
        if (commentary.getId() == null) {
            entityManager.persist(commentary);
            return commentary;
        } else {
            return entityManager.merge(commentary);
        }
    }

    @Override
    public List<Commentary> findAll() {
        TypedQuery<Commentary> query = entityManager.createQuery("select c from Commentary c ",
                Commentary.class);
        return query.getResultList();
    }

    @Override
    public Commentary findById(Long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph",
                entityManager.getEntityGraph("Commentary.Book.Author.Genre"));
        return entityManager.find(Commentary.class, id, properties);
    }

    @Override
    public int delete(Long id) {
        var query = entityManager.createQuery("delete from Commentary с where с.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
