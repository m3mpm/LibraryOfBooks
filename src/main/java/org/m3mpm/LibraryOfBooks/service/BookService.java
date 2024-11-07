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
            throw new BookException("EmptyLibrary");
        return books;
    }

    public Book getBook(Long id){
        Optional<Book> optionalBook = bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
        if(optionalBook.isEmpty()){
            throw new BookException("NoBook");
        }
        return optionalBook.get();
    }

    @Transactional
    public Book saveNewBook(Book newBook){
        return bookRepository.save(newBook);
    }

    @Transactional
    public void updateBook(Long id, Book editedBook){
        bookRepository.updateBook(id,editedBook.getTitle(),editedBook.getAuthor(),editedBook.getPublishedDate());
    }

    @Transactional
    public void deleteBookById(Long id){
        bookRepository.deleteBookById(id);
    }

}
