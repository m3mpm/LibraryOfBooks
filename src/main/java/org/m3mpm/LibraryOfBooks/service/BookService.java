package org.m3mpm.LibraryOfBooks.service;

import org.m3mpm.LibraryOfBooks.exception.BookException;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();
        if (books.isEmpty())
            throw new BookException("EMPTY_LIBRARY");
        return books;
    }

    public Book getBook(Long id){
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if(optionalBook.isEmpty()){
            throw new BookException("NOT_FOUND");
        }
        return optionalBook.get();
    }

    @Transactional
    public Book saveNewBook(Book newBook){
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getTitle().equals(newBook.getTitle())).findAny();
        if(optionalBook.isPresent())
            throw new BookException("EXISTS");
        return bookRepository.save(newBook);
    }

    @Transactional
    public void deleteBookById(Long id){
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if(optionalBook.isEmpty()){
            throw new BookException("NOT_DELETE");
        }
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book updateBook(Long id, Book editedBook){
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if(optionalBook.isEmpty()){
            throw new BookException("NOT_UPDATE");
        }
        Book updatedBook = optionalBook.get();
        updatedBook.setTitle(editedBook.getTitle());
        updatedBook.setAuthor(editedBook.getAuthor());
        updatedBook.setPublishedDate(editedBook.getPublishedDate());
        return bookRepository.save(updatedBook);
    }

}
