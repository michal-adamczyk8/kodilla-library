package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getDateOfCreatingAccount(),
                readerDto.getLendings());
    }

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getTitleId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublish(),
                titleDto.getBooks());
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

    public Lending mapToLending(LendingDto lendingDto) {
        return new Lending(
                lendingDto.getReaderId(),
                lendingDto.getBookId(),
                lendingDto.getDateOfLending(),
                lendingDto.getDateOfReturning());
    }




}
