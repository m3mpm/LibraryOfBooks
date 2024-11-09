package org.m3mpm.LibraryOfBooks.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.m3mpm.LibraryOfBooks.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookListener {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    @Autowired
    public BookListener(BookService bookService, ObjectMapper objectMapper) {
        this.bookService = bookService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void receiveMessage(String message){
        String[] parts = message.split(" , ");
        String action = parts[0];
        String bookData = parts[1];
        Long id = Long.valueOf(0);
        Book book;

        try{
            switch (action) {
                case "ADD":
                    // Создайте и сохраните новую книгу
                    book = objectMapper.readValue(bookData, Book.class);  // Десериализация JSON в объект Book
                    bookService.saveNewBook(book);
                    break;
                case "UPDATE":
                    // Обновите книгу по ID
                    id = Long.parseLong(parts[2]);
                    book = objectMapper.readValue(bookData, Book.class);
                    bookService.updateBook(id, book);
                    break;
                case "DELETE":
                    // Удалите книгу по ID
                    id = Long.parseLong(bookData);
                    bookService.deleteBookById(id);
                    break;
                default:
                    break;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
