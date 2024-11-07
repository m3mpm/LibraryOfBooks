package org.m3mpm.LibraryOfBooks.aspect;

import org.m3mpm.LibraryOfBooks.error.ErrorDetails;
import org.m3mpm.LibraryOfBooks.exception.BookException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorDetails> handleBookException(BookException ex) {
        String errorMessage = ex.getMessage(); // Получаем сообщение из исключения
        ErrorDetails errorDetails = new ErrorDetails(); // Создаем объект ErrorDetails и устанавливаем сообщение

        errorMessage = switch (errorMessage){
            case "EmptyLibrary" -> "List of Books is empty!";
            case "NoBook" -> "This book is missing from the library";
            default -> "Unknown error!";
        };
        errorDetails.setMessage(errorMessage);

        return ResponseEntity.badRequest().body(errorDetails); // Возвращаем ответ с соответствующим сообщением
    }
}
