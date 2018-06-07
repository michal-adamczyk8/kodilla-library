package com.crud.library.service;

import com.crud.library.controller.BookNotAvailbleException;
import com.crud.library.controller.BookNotFoundException;
import com.crud.library.controller.ReaderNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingService {
    @Autowired
    DbService dbService;

    @Autowired
    Mapper mapper;

    public BookDto changingStatus(long bookId, String newStatus) throws BookNotFoundException {
        if (!dbService.getBook(bookId).isPresent()) {
            throw new BookNotFoundException();
        }
        BookDto retrievedBook = mapper.mapToBookDto(dbService.getBook(bookId).get());
        retrievedBook.changeStatus(newStatus);
        dbService.saveBook(mapper.mapToBook(retrievedBook));
        return retrievedBook;
    }

    public void lendBook(LendingDto lendingDto) throws Exception {
        if (checkIfLendingOk(lendingDto)) {
            ReaderDto readerDto = mapper.mapToReaderDto(dbService.getReader(lendingDto.getReaderId()).get());
            readerDto.getLendings().add(lendingDto);
            dbService.saveReader(mapper.mapToReader(readerDto));
            BookDto bookDto = mapper.mapToBookDto(dbService.getBook(lendingDto.getBookId()).get());
            bookDto.changeStatus("rented");
            dbService.saveBook(mapper.mapToBook(bookDto));
        }
    }

    public void returnBook(LendingDto lendingDto) throws Exception {
        if (checkIfLendingOk(lendingDto)) {
            ReaderDto readerDto = mapper.mapToReaderDto(dbService.getReader(lendingDto.getReaderId()).get());
            readerDto.getLendings().remove(lendingDto);
            dbService.saveReader(mapper.mapToReader(readerDto));
            BookDto bookDto = mapper.mapToBookDto(dbService.getBook(lendingDto.getBookId()).get());
            bookDto.changeStatus("available");
            dbService.saveBook(mapper.mapToBook(bookDto));
        }
    }

    public boolean checkIfLendingOk(LendingDto lendingDto) throws Exception {
        boolean isLendingOk = false;
        Lending lending = mapper.mapToLending(lendingDto);
        if (!dbService.getReader(lending.getReaderId()).isPresent()) {
            throw new ReaderNotFoundException();
        } else if (!dbService.getBook(lending.getBookId()).isPresent()) {
            throw new BookNotFoundException();

        } else if ((dbService.getBook(lending.getBookId()).get().getStatus() == "available")) {
            throw new BookNotAvailbleException();
        }
        isLendingOk = true;
        return isLendingOk;
    }
}
