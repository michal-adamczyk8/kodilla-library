package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MapperTestSuite {
    @InjectMocks
    Mapper mapper;

    @Test
    public void shouldMapToReader() {
        //Given
        LendingDto lendingDto1 = new LendingDto(1l, 1l, 1l,
                new Date(2018,10,10), new Date(2018, 11,10));
        LendingDto lendingDto2 = new LendingDto(2l, 1l, 2l,
                new Date(2017,10,1), new Date(2018, 1,27));
        LendingDto lendingDto3 = new LendingDto(3l, 1l, 3l,
                new Date(2018,10,10), new Date(2018, 11,10));
        ReaderDto readerDto = new ReaderDto(1l, "Michał", "Adamczyk",
                new Date(2015, 5, 5), Arrays.asList(lendingDto1, lendingDto2, lendingDto3));
        //When
        Reader reader = mapper.mapToReader(readerDto);

        //Then
        Assert.assertEquals(Reader.class, reader.getClass());
    }

    @Test
    public void shouldMapToReaderDto() {
        //Given
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                new Date(2018, 1, 2), new ArrayList<>());
        //When
        ReaderDto readerDto = mapper.mapToReaderDto(reader);

        //Then
        Assert.assertEquals(ReaderDto.class, readerDto.getClass());
    }


    @Test
    public void shouldMapToLendingList() {
        //Given
        LendingDto lendingDto1 = new LendingDto(1L, 1L, 1L,
                new Date(2017, 5, 5), new Date(2017, 8, 19));
        LendingDto lendingDto2 = new LendingDto(2l, 3L, 11L,
                new Date(2015, 5, 25), new Date(2018, 12, 12));
        LendingDto lendingDto3 = new LendingDto(3l, 124L, 11L,
                new Date(2018, 11, 05), new Date(2018, 12, 31));

        Lending lending1 = new Lending(1L, 1L, 1L,
                new Date(2017, 5, 5), new Date(2017, 8, 19));
        Lending lending2 = new Lending(2L, 3L, 11L,
                new Date(2015, 05, 25), new Date(2018, 12, 12));
        Lending lending3 = new Lending(3L, 124L, 11L,
                new Date(2018, 11, 05), new Date(2018, 12, 31));

        List<LendingDto> lendingDtoList = Arrays.asList(lendingDto1, lendingDto2, lendingDto3);

        //When
        List<Lending> lendingsList = mapper.mapToLendingList(lendingDtoList);

        //Then
        Assert.assertEquals(3, lendingsList.size());
        Assert.assertEquals(lending1.getBookId(), lendingDto1.getBookId());
        Assert.assertEquals(lending1.getLendingId(), lendingDto1.getLendingId());
        Assert.assertEquals(lending1.getDateOfLending(), lendingDto1.getDateOfLending());
        Assert.assertEquals(lending1.getDateOfReturning(), lendingDto1.getDateOfReturning());
        Assert.assertEquals(lending1.getReaderId(), lendingDto1.getReaderId());
        Assert.assertEquals(lending2.getBookId(), lendingDto2.getBookId());
        Assert.assertEquals(lending2.getLendingId(), lendingDto2.getLendingId());
        Assert.assertEquals(lending2.getDateOfLending(), lendingDto2.getDateOfLending());
        Assert.assertEquals(lending2.getDateOfReturning(), lendingDto2.getDateOfReturning());
        Assert.assertEquals(lending2.getReaderId(), lendingDto2.getReaderId());
        Assert.assertEquals(lending3.getBookId(), lendingDto3.getBookId());
        Assert.assertEquals(lending3.getLendingId(), lendingDto3.getLendingId());
        Assert.assertEquals(lending3.getDateOfLending(), lendingDto3.getDateOfLending());
        Assert.assertEquals(lending3.getDateOfReturning(), lendingDto3.getDateOfReturning());
        Assert.assertEquals(lending3.getReaderId(), lendingDto3.getReaderId());
    }

    @Test
    public void shouldMapToLendingDtoList() {
        //Given
        LendingDto lendingDto1 = new LendingDto(1L, 1L, 1L,
                new Date(2017, 5, 5), new Date(2017, 8, 19));
        LendingDto lendingDto2 = new LendingDto(2l, 3l, 11L,
                new Date(2015, 5, 25), new Date(2018, 12, 12));

        Lending lending1 = new Lending(1L, 1L, 1L,
                new Date(2017, 5, 5), new Date(2017, 8, 19));
        Lending lending2 = new Lending(2L, 3L, 11L,
                new Date(2015, 5, 25), new Date(2018, 12, 12));

        List<Lending> lendingList = Arrays.asList(lending1, lending2);

        //When
        List<LendingDto> lendingDtoList = mapper.mapToLendingDtoList(lendingList);

        //Then
        Assert.assertEquals(2, lendingDtoList.size());
        Assert.assertEquals(lendingDto1.getBookId(), lending1.getBookId());
        Assert.assertEquals(lendingDto1.getBookId(), lending1.getLendingId());
        Assert.assertEquals(lendingDto1.getDateOfLending(), lending1.getDateOfLending());
        Assert.assertEquals(lendingDto1.getDateOfReturning(), lending1.getDateOfReturning());

        Assert.assertEquals(lendingDto2.getBookId(), lending2.getBookId());
        Assert.assertEquals(lendingDto2.getLendingId(), lending2.getLendingId());
        Assert.assertEquals(lendingDto2.getDateOfLending(), lending2.getDateOfLending());
        Assert.assertEquals(lendingDto2.getDateOfReturning(), lending2.getDateOfReturning());
    }

    @Test
    public void shouldMapToTitle() {
        //Given
        BookDto bookDto1 = new BookDto(1L, 1L, "Rented");
        BookDto bookDto2 = new BookDto(2L, 1L, "Rented");
        BookDto bookDto3 = new BookDto(3L, 1L, "Available");
        TitleDto titleDto = new TitleDto(1L, "Lalka", "Bolesław Prus", 1893,
                Arrays.asList(bookDto1, bookDto2, bookDto3));
        //When
        Title title = mapper.mapToTitle(titleDto);

        //Then
        Assert.assertEquals(Title.class, title.getClass());
    }

    @Test
    public void shouldMapToListBook() {
        //Given
        BookDto bookDto1 = new BookDto(1L, 1L, "available");
        BookDto bookDto2 = new BookDto(2L, 1L, "rented");
        BookDto bookDto3 = new BookDto(3L, 1L, "available");
        List<BookDto> bookDtoList = Arrays.asList(bookDto1, bookDto2, bookDto3);

        //When
        List<Book> bookList = mapper.mapToListBook(bookDtoList);

        //Given
        Assert.assertEquals(3, bookList.size());
        Assert.assertEquals(1L, bookList.get(0).getBookId());
        Assert.assertEquals(1L, bookList.get(0).getTitleId());
        Assert.assertEquals("available", bookList.get(0).getStatus());
        Assert.assertEquals(2L, bookList.get(1).getBookId());
        Assert.assertEquals(1L, bookList.get(1).getTitleId());
        Assert.assertEquals("rented", bookList.get(1).getStatus());
        Assert.assertEquals(3L, bookList.get(2).getBookId());
        Assert.assertEquals(1L, bookList.get(2).getTitleId());
        Assert.assertEquals("available", bookList.get(2).getStatus());
    }

    @Test
    public void shouldMapToBook() {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, "Rented");

        //When
        Book book = mapper.mapToBook(bookDto);

        //Then
        Assert.assertEquals(Book.class, book.getClass());
    }

    @Test
    public void shouldMapToBookDto() {
        //Given
        Book book = new Book(1L, 1L, "Available");

        //When
        BookDto bookDto = mapper.mapToBookDto(book);

        //Then
        Assert.assertEquals(BookDto.class, bookDto.getClass());
    }

    @Test
    public void shouldMapToLending() {
        //Given
        LendingDto lendingDto = new LendingDto(1L, 1L, 1L,
                new Date(2018, 1, 30), new Date(2018, 3, 24));

        //When
        Lending lending = mapper.mapToLending(lendingDto);

        //Then
        Assert.assertEquals(Lending.class, lending.getClass());
    }

    @Test
    public void shouldMapToLendingDto() {
        //Given
        Lending lending = new Lending(1L, 1L, 1L,
                new Date(2018, 1, 1), new Date(2018, 2, 2));

        //When
        LendingDto lendingDto = mapper.mapToLendingDto(lending);

        //Then
        Assert.assertEquals(LendingDto.class, lendingDto.getClass());
    }
}
