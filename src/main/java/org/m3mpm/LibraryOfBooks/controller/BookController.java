package org.m3mpm.LibraryOfBooks.controller;

import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@Controller
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

    @GetMapping("/new")
    public String newBook(@ModelAttribute("newBook") Book newBook){
        return "books/newBook";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book newBook){
        bookService.saveNewBook(newBook);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(newBook);
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
//        Optional<Book> optionalBook = bookService.getBook(id);
//        if(optionalBook.isPresent()){
//            Book book = optionalBook.get();
//            model.addAttribute("editedBook",book);
//            return "/books/editBook";
//        }
        return "redirect:/books";
    }

    @PostMapping ("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute("editedBook") Book editedBook){
        bookService.updateBook(id, editedBook);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBookById(@PathVariable("id") Long id){
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    /* ver. 2 delete through Book object
    @PostMapping("/delete")
    public String deleteBook(@ModelAttribute("deletedBook") Book deletedBook){
        bookService.deleteBook(deletedBook);
        return "redirect:/books";
    }
    */

    /* New version of methods */

    /*
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        Book savedBook = bookService.saveNewBook(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable("id") Long id, @RequestBody Book editedBook) {
        Optional<Book> optionalBook = bookService.getBook(id);
        if (optionalBook.isPresent()) {
            Book updatedBook = bookService.updateBook(id, editedBook);
            return ResponseEntity.ok(updatedBook);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

     */

}
