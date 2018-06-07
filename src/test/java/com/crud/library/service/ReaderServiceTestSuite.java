package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    Mapper mapper;

    @Mock
    DbService dbService;

    @Test
    public void shouldAddReader() {
        //Given
        Reader reader = new Reader(1l, "Michał", "Adamczyk", new Date(2018, 01, 01),
                new ArrayList<>());
        ReaderDto readerDto = new ReaderDto(1l, "Michał", "Adamczyk", new Date(2018, 01, 01),
                new ArrayList<>());

        when(dbService.saveReader(any())).thenReturn(reader);
        when(mapper.mapToReader(readerDto)).thenReturn(reader);

        //When
        readerService.addingUser(readerDto);

        //Then
        verify(dbService, times(1)).saveReader(any());
        verify(mapper, times(1)).mapToReader(any());

    }
}
