package com.crud.library.mapper;

import com.crud.library.domain.LendingDto;
import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReaderMapperTestSuite {
    @InjectMocks
    ReaderMapper readerMapper;

    @Mock
    LendingMapper lendingMapper;

    @Test
    public void shouldMapToReader() {
        //Given
        ReaderDto readerDto = new ReaderDto(1l, "Michał", "Adamczyk",
                "2015-05-05", new ArrayList<>());
        when(lendingMapper.mapToLendingList(any())).thenReturn(new ArrayList<>());

        //When
        Reader reader = readerMapper.mapToReader(readerDto);

        //Then
        Assert.assertEquals(Reader.class, reader.getClass());
    }

    @Test
    public void shouldMapToReaderDto() {
        //Given
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                LocalDate.of(2018, 1, 2), new ArrayList<>());

        when(lendingMapper.mapToLendingDtoList(anyList())).thenReturn(new ArrayList<>());

        //When
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);

        //Then
        Assert.assertEquals(ReaderDto.class, readerDto.getClass());
    }
}
