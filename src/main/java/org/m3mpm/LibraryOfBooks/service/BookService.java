package org.m3mpm.LibraryOfBooks.service;

import org.m3mpm.LibraryOfBooks.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();

    BookDto getBook(Long id);

    BookDto saveBook(BookDto bookDto);

    BookDto updateBook(Long id, BookDto bookDto);

    void deleteBook(Long id);
}
