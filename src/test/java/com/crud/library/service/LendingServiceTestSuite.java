package com.crud.library.service;

import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.LendingMapper;
import com.crud.library.mapper.ReaderMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LendingServiceTestSuite {

    @InjectMocks
    LendingService lendingService;

    @Mock
    DbService dbService;

    @Mock
    BookMapper bookMapper;

    @Mock
    ReaderMapper readerMapper;

    @Mock
    LendingMapper lendingMapper;

    @Test
    public void shouldChangeStatus() {
        //Given
        Book book = new Book(1L, 1L, BookStatus.AVAILABLE);
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.AVAILABLE);

        when(dbService.getBook(anyLong())).thenReturn(Optional.of(book));
        when(bookMapper.mapToBookDto(any())).thenReturn(bookDto);

        //When
        BookDto retrievedBookDto = lendingService.changingStatus(1L, BookStatus.RENTED);

        //Then
        Assert.assertEquals(BookStatus.RENTED, retrievedBookDto.getStatus());
    }

    @Test
    public void shouldLendBook() {
        //Given
        LendingDto lendingDto = new LendingDto(1L, 1L, 1L,
                "2018-01-01", "2018-03-03");
        Lending lending = new Lending(1L, 1L, 1L,
                LocalDate.of(2018, 1, 1), LocalDate.of(2018, 3, 3));
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.AVAILABLE);
        Book book = new Book(1L, 1L, BookStatus.AVAILABLE);
        ReaderDto readerDto = new ReaderDto(1L, "Michał", "Adamczyk",
                "2018-01-01", new ArrayList<>());
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                LocalDate.of(2018, 1, 1), new ArrayList<>());

        when(dbService.getReader(anyLong())).thenReturn(Optional.of(reader));
        when(readerMapper.mapToReaderDto(any())).thenReturn(readerDto);
        when(dbService.saveReader(any())).thenReturn(reader);
        when(readerMapper.mapToReader(any())).thenReturn(reader);
        when(dbService.getBook(anyLong())).thenReturn(Optional.of(book));
        when(bookMapper.mapToBookDto(any())).thenReturn(bookDto);
        when(dbService.saveBook(any())).thenReturn(book);
        when(bookMapper.mapToBook(any())).thenReturn(book);
        when(lendingMapper.mapToLending(any())).thenReturn(lending);

        //When
        lendingService.lendBook(lendingDto);
        LendingDto retrievedLendingDto = readerDto.getLendings().get(0);

        //Then
        Assert.assertEquals(1, readerDto.getLendings().size());
        Assert.assertEquals(1L, retrievedLendingDto.getReaderId());
        Assert.assertEquals(1L, retrievedLendingDto.getLendingId());
        Assert.assertEquals(1L, retrievedLendingDto.getBookId());
        Assert.assertEquals("rented", bookDto.getStatus().toString());
    }

    @Test
    public void shouldReturnBook() {
        //Given
        LendingDto lendingDto = new LendingDto(1L, 1L, 1L,
                "2018-01-01", "2018-03-03");
        Lending lending = new Lending(1L, 1L, 1L,
                LocalDate.of(2018, 1, 1), LocalDate.of(2018, 3, 3));
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.AVAILABLE);
        Book book = new Book(1L, 1L, BookStatus.AVAILABLE);
        List<LendingDto> lendingDtoList = new ArrayList<>();
        lendingDtoList.add(lendingDto);
        ReaderDto readerDto = new ReaderDto(1L, "Michał", "Adamczyk",
                "2018-01-01", lendingDtoList);
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                LocalDate.of(2018, 1, 1), Arrays.asList(lending));

        when(dbService.getReader(anyLong())).thenReturn(Optional.of(reader));
        when(readerMapper.mapToReaderDto(any())).thenReturn(readerDto);
        when(dbService.saveReader(any())).thenReturn(reader);
        when(readerMapper.mapToReader(any())).thenReturn(reader);
        when(dbService.getBook(anyLong())).thenReturn(Optional.of(book));
        when(bookMapper.mapToBookDto(any())).thenReturn(bookDto);
        when(dbService.saveBook(any())).thenReturn(book);
        when(bookMapper.mapToBook(any())).thenReturn(book);
        when(lendingMapper.mapToLending(any())).thenReturn(lending);

        //When
        lendingService.returnBook(lendingDto);

        //Then
        Assert.assertEquals(0, readerDto.getLendings().size());
    }


}
