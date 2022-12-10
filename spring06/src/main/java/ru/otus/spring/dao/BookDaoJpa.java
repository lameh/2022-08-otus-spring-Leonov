package ru.otus.spring.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoJpa implements BookDao {

    private final EntityManager entityManager;

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(id) from Book", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b " +
                "join fetch b.author " +
                "join fetch b.genre ", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Long id) {
        Map<String, Object> properties = Map.of("javax.persistence.fetchgraph", entityManager.getEntityGraph("Book.Author.Genre"));
        return entityManager.find(Book.class, id, properties);
    }

    @Override
    public int delete(Long id) {
        Query query = entityManager.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
