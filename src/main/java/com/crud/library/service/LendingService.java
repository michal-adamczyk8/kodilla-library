package com.crud.library.service;

import com.crud.library.exceptions.BookNotAvailbleException;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.LendingMapper;
import com.crud.library.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LendingService {
    @Autowired
    DbService dbService;

    @Autowired
    LendingMapper lendingMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    ReaderMapper readerMapper;

    public BookDto changingStatus(long bookId, BookStatus newStatus) {
        try {
            if (!dbService.getBook(bookId).isPresent()) {
                throw new BookNotFoundException();
            }
            BookDto retrievedBook = bookMapper.mapToBookDto(dbService.getBook(bookId).get());
            retrievedBook.changeStatus(newStatus);
            dbService.saveBook(bookMapper.mapToBook(retrievedBook));
            return retrievedBook;
        } catch (BookNotFoundException e) {
            System.out.println(e);
        }
        return new BookDto();
    }

    public void lendBook(LendingDto lendingDto) {
        try {
            if (checkIfLendingOk(lendingDto)) {
                ReaderDto readerDto = readerMapper.mapToReaderDto(dbService.getReader(lendingDto.getReaderId()).get());
                readerDto.getLendings().add(lendingDto);
                dbService.saveReader(readerMapper.mapToReader(readerDto));
                BookDto bookDto = bookMapper.mapToBookDto(dbService.getBook(lendingDto.getBookId()).get());
                bookDto.changeStatus(BookStatus.RENTED);
                dbService.saveBook(bookMapper.mapToBook(bookDto));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void returnBook(LendingDto lendingDto) {
        try {
            if (checkIfBookAndReaderExists(lendingDto)) {
                ReaderDto readerDto = readerMapper.mapToReaderDto(dbService.getReader(lendingDto.getReaderId()).get());
                readerDto.getLendings().remove(lendingDto);
                dbService.saveReader(readerMapper.mapToReader(readerDto));
                BookDto bookDto = bookMapper.mapToBookDto(dbService.getBook(lendingDto.getBookId()).get());
                bookDto.changeStatus(BookStatus.AVAILABLE);
                dbService.saveBook(bookMapper.mapToBook(bookDto));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean checkIfLendingOk(LendingDto lendingDto) {
        boolean isLendingOk = false;
        try {
            Lending lending = lendingMapper.mapToLending(lendingDto);
            if(!(dbService.getBook(lending.getBookId()).get().getStatus().toString() == "available")) {
                throw new BookNotAvailbleException();
            }
            else if (checkIfBookAndReaderExists(lendingDto)) {
                isLendingOk = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return isLendingOk;
    }

    public boolean checkIfBookAndReaderExists(LendingDto lendingDto) {
        boolean isLendingOk = false;
        try {
            Lending lending = lendingMapper.mapToLending(lendingDto);
            if (!dbService.getReader(lending.getReaderId()).isPresent()) {
                throw new ReaderNotFoundException();
            } else if (!dbService.getBook(lending.getBookId()).isPresent()) {
                throw new BookNotFoundException();
            }
            isLendingOk = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return isLendingOk;
    }
}
