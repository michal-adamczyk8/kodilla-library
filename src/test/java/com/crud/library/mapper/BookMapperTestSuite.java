package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.BookStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookMapperTestSuite {

    @InjectMocks
    BookMapper bookMapper;

    @Test
    public void shouldMapToListBook() {
        //Given
        BookDto bookDto1 = new BookDto(1L, 1L, BookStatus.AVAILABLE);
        BookDto bookDto2 = new BookDto(2L, 1L, BookStatus.RENTED);
        BookDto bookDto3 = new BookDto(3L, 1L, BookStatus.AVAILABLE);
        List<BookDto> bookDtoList = Arrays.asList(bookDto1, bookDto2, bookDto3);

        //When
        List<Book> bookList = bookMapper.mapToListBook(bookDtoList);

        //Given
        Assert.assertEquals(3, bookList.size());
        Assert.assertEquals(1L, bookList.get(0).getBookId());
        Assert.assertEquals(1L, bookList.get(0).getTitleId());
        Assert.assertEquals("available", bookList.get(0).getStatus().toString());
        Assert.assertEquals(2L, bookList.get(1).getBookId());
        Assert.assertEquals(1L, bookList.get(1).getTitleId());
        Assert.assertEquals("rented", bookList.get(1).getStatus().toString());
        Assert.assertEquals(3L, bookList.get(2).getBookId());
        Assert.assertEquals(1L, bookList.get(2).getTitleId());
        Assert.assertEquals("available", bookList.get(2).getStatus().toString());
    }

    @Test
    public void shouldMapToBook() {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.RENTED);

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        Assert.assertEquals(Book.class, book.getClass());
    }

    @Test
    public void shouldMapToBookDto() {
        //Given
        Book book = new Book(1L, 1L, BookStatus.AVAILABLE);

        //When
        BookDto bookDto = bookMapper.mapToBookDto(book);

        //Then
        Assert.assertEquals(BookDto.class, bookDto.getClass());
    }
}
