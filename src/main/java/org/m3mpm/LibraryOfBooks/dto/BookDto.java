package org.m3mpm.LibraryOfBooks.dto;

import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BookDto {
    private String title;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;

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
}
