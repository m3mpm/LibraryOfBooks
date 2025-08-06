package org.m3mpm.LibraryOfBooks.service;

import org.m3mpm.LibraryOfBooks.dto.BookDto;
import org.m3mpm.LibraryOfBooks.exception.BookException;
import org.m3mpm.LibraryOfBooks.mapper.BookMapper;
import org.m3mpm.LibraryOfBooks.entity.BookEntity;
import org.m3mpm.LibraryOfBooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImp(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookRepository.getAllBooks();
        if (books.isEmpty())
            throw new BookException("EMPTY_LIBRARY");
        return books.stream().map(bookMapper::bookToBookDto).collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BookException("NOT_FOUND"));
        return bookMapper.bookToBookDto(book);
    }

    @Transactional
    public BookDto saveBook(BookDto newBookDto) {
        Optional<BookEntity>  optionalBook = bookRepository.findByTitleAndAuthorAndPublishedDate(newBookDto.getTitle(), newBookDto.getAuthor(), newBookDto.getPublishedDate());
        if (optionalBook.isPresent())
            throw new BookException("EXISTS");
        return bookMapper.bookToBookDto(bookRepository.save(bookMapper.bookDtoToBook(newBookDto)));
    }

    @Transactional
    public BookDto updateBook(Long id, BookDto editedBookDto) {
        BookEntity book = bookRepository.findById(id).orElseThrow(() -> new BookException("NOT_UPDATE"));
        BookEntity updatedBook = bookMapper.bookDtoToBook(editedBookDto);
        updatedBook.setId(book.getId());
        updatedBook.setCreated_at(book.getCreated_at());
        return bookMapper.bookToBookDto(bookRepository.save(updatedBook));
    }

    @Transactional
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)) throw new BookException("NOT_DELETE");
        bookRepository.deleteById(id);
    }
}
