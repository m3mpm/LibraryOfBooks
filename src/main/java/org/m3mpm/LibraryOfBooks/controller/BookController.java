package org.m3mpm.LibraryOfBooks.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.m3mpm.LibraryOfBooks.dto.BookDto;
import org.m3mpm.LibraryOfBooks.rabbitmq.MessageSender;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final MessageSender messageSender;

    @GetMapping()
    public ResponseEntity<List<BookDto>> showAllBooks(){
        List<BookDto> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> showBook(@PathVariable("id") Long id){
        BookDto bookDto =  bookService.getBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto newBookDto){
        BookDto bookDto = bookService.saveNewBook(newBookDto);
//        messageSender.sendBookMessage("ADD", newBookDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BookDto> editBook(@PathVariable("id") Long id, @Valid @RequestBody BookDto editedBookDto) {
        BookDto bookDto = bookService.updateBook(id, editedBookDto);
//        messageSender.sendBookMessage("UPDATE", id, editedBookDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
//        messageSender.sendBookMessage("DELETE", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
