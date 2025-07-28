package org.m3mpm.LibraryOfBooks.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

    @Entity
    @Table(name = "books")
    public class BookEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
        @SequenceGenerator(name = "book_seq", sequenceName = "book_sequence", allocationSize = 1)
        @Column(name = "id")
        private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public BookEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity book = (BookEntity) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publishedDate, book.publishedDate) && Objects.equals(created_at, book.created_at) && Objects.equals(updated_at, book.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publishedDate, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishedDate=" + publishedDate +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
