package com.crud.library.service;

import com.crud.library.controller.BookNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LendingServiceTestSuite {

    @InjectMocks
    LendingService lendingService;

    @Mock
    DbService dbService;

    @Mock
    Mapper mapper;


    @Test
    public void shouldChangeStatus() throws BookNotFoundException {
        //Given
        Book book = new Book(1l, 1l, "available");
        BookDto bookDto = new BookDto(1l, 1l, "available");

        when(dbService.getBook(anyLong())).thenReturn(Optional.of(book));
        when(mapper.mapToBookDto(any())).thenReturn(bookDto);

        //When
        BookDto retrievedBookDto = lendingService.changingStatus(1l, "rented");

        //Then
        Assert.assertEquals("rented", retrievedBookDto.getStatus());
    }

    @Test
    public void shouldLendBook() throws Exception {
        //Given
        LendingDto lendingDto = new LendingDto(1L, 1L, 1L,
                new Date(2018, 1, 1), new Date(2018, 3, 3));
        Lending lending = new Lending(1L, 1L, 1L,
                new Date(2018, 1, 1), new Date(2018, 3, 3));
        BookDto bookDto = new BookDto(1L, 1L, "available");
        Book book = new Book(1L, 1L, "available");
        ReaderDto readerDto = new ReaderDto(1L, "Michał", "Adamczyk",
                new Date(2018, 1, 1), new ArrayList<>());
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                new Date(2018, 1, 1), new ArrayList<>());

        when(dbService.getReader(anyLong())).thenReturn(Optional.of(reader));
        when(mapper.mapToReaderDto(any())).thenReturn(readerDto);
        when(dbService.saveReader(any())).thenReturn(reader);
        when(mapper.mapToReader(any())).thenReturn(reader);
        when(dbService.getBook(anyLong())).thenReturn(Optional.of(book));
        when(mapper.mapToBookDto(any())).thenReturn(bookDto);
        when(dbService.saveBook(any())).thenReturn(book);
        when(mapper.mapToBook(any())).thenReturn(book);

        //When
        lendingService.lendBook(lendingDto);
        LendingDto retrievedLendingDto = readerDto.getLendings().get(0);

        //Then
        Assert.assertEquals(1, readerDto.getLendings().size());
        Assert.assertEquals(1L, retrievedLendingDto.getReaderId());
        Assert.assertEquals(1L, retrievedLendingDto.getLendingId());
        Assert.assertEquals(1L, retrievedLendingDto.getBookId());
        Assert.assertEquals(new Date(2018, 1, 1), retrievedLendingDto.getDateOfLending());
        Assert.assertEquals(new Date(2018, 3, 3), retrievedLendingDto.getDateOfReturning());
        Assert.assertEquals("rented", bookDto.getStatus());
    }
}
