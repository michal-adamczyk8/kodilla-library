package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.TitleMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTestSuite {

    @InjectMocks
    BookService bookService;

    @Mock
    TitleMapper titleMapper;

    @Mock
    BookMapper bookMapper;

    @Mock
    DbService dbService;

    @Test
    public void shouldAddTitle() {
        BookDto bookDto1 = new BookDto(1l, 1l, BookStatus.RENTED);
        BookDto bookDto2 = new BookDto(2l, 1l, BookStatus.AVAILABLE);
        Book book1 = new Book(2l, 1l, BookStatus.AVAILABLE);
        Book book2 = new Book(2l, 1l, BookStatus.AVAILABLE);

        TitleDto titleDto = new TitleDto(1l, "Wzgórze psów", "Jakub Żulczyk", 2017,
                Arrays.asList(bookDto1, bookDto2));
        Title title = new Title(1l, "Wzgórze psów", "Jakub Żulczyk", 2017,
                Arrays.asList(book1, book2));

        when(titleMapper.mapToTitle(any())).thenReturn(title);
        when(dbService.saveTitle(any())).thenReturn(title);

        //When
        bookService.addingTitle(titleDto);

        //Then
        verify(titleMapper, times(1)).mapToTitle(any());
        verify(dbService, times(1)).saveTitle(any());
    }

    @Test
    public void shouldAddBook() {
        //Given
        Book book = new Book(1l, 1l, BookStatus.AVAILABLE);
        BookDto bookDto = new BookDto(1l, 1l, BookStatus.AVAILABLE);

        when(bookMapper.mapToBook(bookDto)).thenReturn(book);
        when(dbService.saveBook(book)).thenReturn(book);

        //When
        bookService.addingBook(bookDto);

        //Then
        verify(bookMapper, times(1)).mapToBook(any());
        verify(dbService, times(1)).saveBook(any());
    }

    @Test
    public void shouldGetBookCount() throws TitleNotFoundException {
        //Given
        Book book1 = new Book(1l, 1l, BookStatus.AVAILABLE);
        Book book2 = new Book(2l, 1l, BookStatus.RENTED);
        Book book3 = new Book(3l, 1l, BookStatus.AVAILABLE);
        Book book4 = new Book(4l, 1l, BookStatus.RENTED);

        TitleDto titleDto = new TitleDto(1l, "Wzgórze Psów", "Jakub Żulczyk", 2017,
                new ArrayList<>());
        Title title = new Title(1l, "Wzgórze Psów", "Jakub Żulczyk", 2017,
                Arrays.asList(book1, book2, book3, book4));

        when(dbService.getTitle(anyLong())).thenReturn(Optional.of(title));

        //When
        long bookCount = bookService.gettingBookCount(titleDto.getTitleId());

        //Then
        Assert.assertEquals(2, bookCount);
    }


}
