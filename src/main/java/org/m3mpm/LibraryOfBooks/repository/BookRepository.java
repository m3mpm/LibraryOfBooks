package org.m3mpm.LibraryOfBooks.repository;

import org.m3mpm.LibraryOfBooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b ORDER BY b.id ASC")
    List<Book> getAllBooks();

    @Modifying
    @Query("UPDATE Book b SET b.title = :title, b.author = :author, b.publishedDate = :publishedDate WHERE b.id = :id")
    void updateBook(@Param("id") Long id, @Param("title") String title, @Param("author") String author,
                   @Param("publishedDate") LocalDate publishedDate);

    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = :id")
    void deleteBookById(@Param("id") Long id);
}
