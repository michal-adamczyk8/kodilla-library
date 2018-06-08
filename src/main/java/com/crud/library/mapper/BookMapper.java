package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public List<Book> mapToListBook(List<BookDto> bookDtoList){
        return bookDtoList.stream()
                .map(bookDto -> new Book(bookDto.getBookId(), bookDto.getTitleId(), bookDto.getStatus()))
                .collect(Collectors.toList());
    }

    public Book mapToBook(BookDto bookDto) {
        return new Book(
                bookDto.getBookId(),
                bookDto.getTitleId(),
                bookDto.getStatus());
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(
                book.getBookId(),
                book.getTitleId(),
                book.getStatus());
    }

}
