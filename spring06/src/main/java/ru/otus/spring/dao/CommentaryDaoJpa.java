package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
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
        return entityManager.find(Commentary.class, id);
    }

    @Override
    public int delete(Long id) {
        var comment = entityManager.find(Commentary.class, id);
        entityManager.remove(comment);
        entityManager.flush();
        entityManager.clear();
        var res = entityManager.find(Commentary.class, id);
        return res == null ? 1 : 0;
    }
}
