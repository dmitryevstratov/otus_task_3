package com.otus.task3.repository;

import com.otus.task3.model.entity.Book;
import com.otus.task3.model.entity.Genre;
import com.otus.task3.repository.api.BookRepository;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();
    }

    @Override
    public Book findById(Long id) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("DELETE FROM Book b WHERE b.id = :id")
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Book save(String name, String description, Long idAuthor, String genre) {
        entityManager.createNativeQuery("INSERT INTO Book (name, description, id_author) VALUES (:name, :description, :idAuthor)", Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("idAuthor", idAuthor)
                .executeUpdate();

        List<Book> books = entityManager.createQuery("SELECT b FROM Book b", Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        Genre gn = entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :genre", Genre.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("genre", genre)
                .getSingleResult();

        if (!CollectionUtils.isEmpty(books)) {
            Long bookId = books.get(books.size() - 1).getId();
            entityManager.createNativeQuery("INSERT INTO book_genre (book_id, genre_id) VALUES (:book_id, :genre_id)")
                    .setParameter("book_id", bookId)
                    .setParameter("genre_id", gn.getId())
                    .executeUpdate();

            return books.get(books.size() - 1);
        }

        return null;
    }

    @Override
    public Book update(Long id, String name, String description, Long idAuthor, String genre) {
        entityManager.createQuery("UPDATE Book b SET b.name = :name, b.description = :description, b.author.id = :idAuthor WHERE b.id = :id")
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("id", id)
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("idAuthor", idAuthor)
                .executeUpdate();

        Book book = entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .setParameter("id", id)
                .getSingleResult();

        if (!StringUtils.isEmpty(genre) && book.getGenres().stream().filter(gen -> gen.getName().equals(genre)).findAny().isEmpty()) {
            Genre gn = entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :genre", Genre.class)
                    .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                    .setParameter("genre", genre)
                    .getSingleResult();

            entityManager.createNativeQuery("INSERT INTO book_genre (book_id, genre_id) VALUES (:book_id, :genre_id)")
                    .setParameter("book_id", book.getId())
                    .setParameter("genre_id", gn.getId())
                    .executeUpdate();
        }

        return book;
    }
}
