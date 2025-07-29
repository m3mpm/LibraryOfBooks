package org.m3mpm.LibraryOfBooks.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Publish date must be in the past or present")
    private LocalDate publishedDate;

}
