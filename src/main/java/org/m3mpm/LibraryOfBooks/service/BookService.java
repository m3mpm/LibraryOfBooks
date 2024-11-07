package org.m3mpm.LibraryOfBooks.service;

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

    public Optional<List<Book>> getAllBooks() {
        //  List<Book> books = bookRepository.findAll();
        List<Book> books = bookRepository.getAllBooks();
        return books.isEmpty() ? Optional.empty() : Optional.of(books);
    }

    public Optional<Book> getBook(Long id){
        return bookRepository.findAll().stream().filter(book -> book.getId().equals(id)).findAny();
    }

    @Transactional
    public void saveNewBook(Book newBook){
        bookRepository.save(newBook);
    }

    @Transactional
    public void updateBook(Long id, Book editedBook){
        bookRepository.updateBook(id,editedBook.getTitle(),editedBook.getAuthor(),editedBook.getPublishedDate());
    }

    @Transactional
    public void deleteBookById(Long id){
        bookRepository.deleteBookById(id);
    }

    /* ver.2 delete through Book object
    @Transactional
    public void deleteBook(Book deletedBooks){
        bookRepository.delete(deletedBooks);
    }
     */


}
