package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReaderServiceTestSuite {
    @InjectMocks
    ReaderService readerService;

    @Mock
    ReaderMapper readerMapper;

    @Mock
    DbService dbService;

    @Test
    public void shouldAddReader() {
        //Given
        Reader reader = new Reader(1l, "Michał", "Adamczyk", LocalDate.of(2018, 01, 01),
                new ArrayList<>());
        ReaderDto readerDto = new ReaderDto(1l, "Michał", "Adamczyk", "2018-01-01",
                new ArrayList<>());

        when(dbService.saveReader(any())).thenReturn(reader);
        when(readerMapper.mapToReader(readerDto)).thenReturn(reader);

        //When
        readerService.addingUser(readerDto);

        //Then
        verify(dbService, times(1)).saveReader(any());
        verify(readerMapper, times(1)).mapToReader(any());

    }
}
