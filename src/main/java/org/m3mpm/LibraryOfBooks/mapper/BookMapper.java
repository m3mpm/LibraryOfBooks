package org.m3mpm.LibraryOfBooks.mapper;

import org.m3mpm.LibraryOfBooks.dto.BookDto;
import org.m3mpm.LibraryOfBooks.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookMapper {

    public abstract BookDto bookToBookDto(Book book);

    @Mapping(target = "created_at", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updated_at", expression = "java(java.time.LocalDateTime.now())")
    public abstract Book bookDtoToBook(BookDto bookDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "updated_at", expression = "java(java.time.LocalDateTime.now())")
    public abstract void updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);
}

