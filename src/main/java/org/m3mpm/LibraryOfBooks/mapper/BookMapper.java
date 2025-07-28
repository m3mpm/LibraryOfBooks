package org.m3mpm.LibraryOfBooks.mapper;

import org.m3mpm.LibraryOfBooks.dto.BookDto;
import org.m3mpm.LibraryOfBooks.entity.BookEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BookMapper {

    public abstract BookDto bookToBookDto(BookEntity book);

    @Mapping(target = "created_at", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updated_at", expression = "java(java.time.LocalDateTime.now())")
    public abstract BookEntity bookDtoToBook(BookDto bookDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "updated_at", expression = "java(java.time.LocalDateTime.now())")
    public abstract void updateBookFromBookDto(BookDto bookDto, @MappingTarget BookEntity book);
}

