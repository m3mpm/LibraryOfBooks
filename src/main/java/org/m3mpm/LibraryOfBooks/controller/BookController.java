package org.m3mpm.LibraryOfBooks.controller;

import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public String showAllBooks(Model model){
        Optional<List<Book>> optionalBooks = bookService.getAllBooks();
        if (optionalBooks.isPresent()) {
            List<Book> allBooks = optionalBooks.get();
            model.addAttribute("allBooks",allBooks);
            return "books/allBooks";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") Long id, Model model){
        Optional<Book> optionalBook = bookService.getBook(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            model.addAttribute("book",book);
            return "books/showBook";
        }
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("newBook") Book newBook){
        return "books/newBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("newBook") Book newBook){
        bookService.saveNewBook(newBook);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
        Optional<Book> optionalBook = bookService.getBook(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            model.addAttribute("editedBook",book);
            return "/books/editBook";
        }
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

}
