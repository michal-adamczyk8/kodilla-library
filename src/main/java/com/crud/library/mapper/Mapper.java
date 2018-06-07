package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getDateOfCreatingAccount(),
                mapToLendingList(readerDto.getLendings()));
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getDateOfCreatingAccount(),
                mapToLendingDtoList(reader.getLendings()));
    }

    public List<LendingDto> mapToLendingDtoList(List<Lending> lendings) {
        return lendings.stream()
                .map(lending -> new LendingDto(lending.getLendingId(), lending.getReaderId(), lending.getBookId(),
                        lending.getDateOfLending(), lending.getDateOfReturning()))
                .collect(Collectors.toList());
    }

    public List<Lending> mapToLendingList(List<LendingDto> lendingDtoList) {
        return lendingDtoList.stream()
                .map(lendingDto -> new Lending(lendingDto.getLendingId(), lendingDto.getReaderId(),
                        lendingDto.getBookId(), lendingDto.getDateOfLending(), lendingDto.getDateOfReturning()))
                .collect(Collectors.toList());
    }

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getTitleId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublish(),
                mapToListBook(titleDto.getBooks()));
    }

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

    public Lending mapToLending(LendingDto lendingDto) {
        return new Lending(
                lendingDto.getLendingId(),
                lendingDto.getReaderId(),
                lendingDto.getBookId(),
                lendingDto.getDateOfLending(),
                lendingDto.getDateOfReturning());
    }

    public LendingDto mapToLendingDto(Lending lending) {
        return new LendingDto(
                lending.getLendingId(),
                lending.getReaderId(),
                lending.getBookId(),
                lending.getDateOfLending(),
                lending.getDateOfReturning());
    }
}
