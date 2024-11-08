package org.m3mpm.LibraryOfBooks.controller;

import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<?> showAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showBook(@PathVariable("id") Long id){
        Book book =  bookService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book newBook){
        Book book = bookService.saveNewBook(newBook);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editBook(@PathVariable("id") Long id, @RequestBody Book editedBook) {
        Book updatedBook = bookService.updateBook(id, editedBook);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }

}
