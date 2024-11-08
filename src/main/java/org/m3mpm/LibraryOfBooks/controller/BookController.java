package org.m3mpm.LibraryOfBooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.rabbitmq.MessageSender;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final MessageSender messageSender;

    @Autowired
    public BookController(BookService bookService, MessageSender messageSender, ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.messageSender = messageSender;
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
        messageSender.sendBookMessage("ADD", newBook);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newBook);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editBook(@PathVariable("id") Long id, @RequestBody Book editedBook) {
        messageSender.sendBookMessage("UPDATE", id, editedBook);
        return ResponseEntity.status(HttpStatus.OK).body(editedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
        messageSender.sendBookMessage("DELETE", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
