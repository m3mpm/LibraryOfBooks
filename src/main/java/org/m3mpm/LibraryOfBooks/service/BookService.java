package org.m3mpm.LibraryOfBooks.service;

import org.m3mpm.LibraryOfBooks.dto.BookDto;
import org.m3mpm.LibraryOfBooks.exception.BookException;
import org.m3mpm.LibraryOfBooks.mapper.BookMapper;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty())
            throw new BookException("EMPTY_LIBRARY");
        return books.stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookException("NOT_FOUND"));
        return bookMapper.bookToBookDto(book);
    }

    @Transactional
    public BookDto saveNewBook(BookDto newBookDto) {
        Optional<Book> optionalBook = bookRepository.findAll().stream().
                filter(book -> book.getTitle().equals(newBookDto.getTitle())).
                findAny();
        if (optionalBook.isPresent())
            throw new BookException("EXISTS");
        return bookMapper.bookToBookDto(bookRepository.save(bookMapper.bookDtoToBook(newBookDto)));
    }

    @Transactional
    public BookDto updateBook(Long id, BookDto editedBookDto) {
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if (optionalBook.isEmpty()) {
            throw new BookException("NOT_UPDATE");
        }

        Book updatedBook = bookMapper.bookDtoToBook(editedBookDto);
        updatedBook.setId(optionalBook.get().getId());
        updatedBook.setCreated_at(optionalBook.get().getCreated_at());
        return bookMapper.bookToBookDto(bookRepository.save(updatedBook));
    }

    @Transactional
    public void deleteBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if (optionalBook.isEmpty()) {
            throw new BookException("NOT_DELETE");
        }
        bookRepository.deleteById(id);
    }
}
