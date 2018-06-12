package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.LendingRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {
    @InjectMocks
    DbService dbService;

    @Mock
    ReaderRepository readerRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    TitleRepository titleRepository;

    @Test
    public void shouldSaveReader() {
        //Given
        Reader reader = new Reader(1l, "Michał", "Adamczyk",
                LocalDate.of(2018, 01, 01), new ArrayList<>());
        when(readerRepository.save(reader)).thenReturn(reader);

        //When
        dbService.saveReader(reader);

        //Then
        verify(readerRepository, times(1)).save(any());
    }

    @Test
    public void shouldAddTitle() {
        //Given
        Title title = new Title(1L, "Wzgórze Psów", "Jakub Żulczyk", 2017,
                new ArrayList<>());
        when(titleRepository.save(any())).thenReturn(title);

        //When
        dbService.saveTitle(title);

        //Given
        verify(titleRepository, times(1)).save(any());
    }

    @Test
    public void getTitle() {
        //Given
        Title title = new Title(1l, "Wzgórze Psów", "Jakub Żulczyk", 2017,
                new ArrayList<>());
        when(titleRepository.findById(title.getTitleId())).thenReturn(Optional.of(title));

        //When
        dbService.getTitle(title.getTitleId());

        //Then
        verify(titleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void shouldGetBook() {
        //Given
        Book book = new Book(1l, 1l, BookStatus.AVAILABLE);
        when(bookRepository.findBookByBookId(book.getBookId())).thenReturn(Optional.of(book));

        //When
        dbService.getBook(book.getBookId());

        //Then
        verify(bookRepository, times(1)).findBookByBookId(anyLong());
    }

    @Test
    public void shouldSaveBook() {
        //Given
        Book book = new Book(1l, 1l, BookStatus.AVAILABLE);
        when(bookRepository.save(book)).thenReturn(book);

        //When
        dbService.saveBook(book);

        //Then
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    public void shouldGetTitle() {
        //Given
        Title title = new Title(1l, "Wzgórze Psów", "Jakub Żulczyk", 2017,
                new ArrayList<>());
        when(titleRepository.save(title)).thenReturn(title);

        //When
        dbService.saveTitle(title);

        //Then
        verify(titleRepository, times(1)).save(any());
    }
}
