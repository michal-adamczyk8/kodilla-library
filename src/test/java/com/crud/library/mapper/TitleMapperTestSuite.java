package com.crud.library.mapper;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TitleMapperTestSuite {

    @InjectMocks
    TitleMapper titleMapper;

    @Mock
    BookMapper bookMapper;

    @Test
    public void shouldMapToTitle() {
        //Given
        TitleDto titleDto = new TitleDto(1L, "Lalka", "Bolesław Prus", 1893,
                new ArrayList<>());
        when(bookMapper.mapToListBook(anyList())).thenReturn(new ArrayList<>());
        //When
        Title title = titleMapper.mapToTitle(titleDto);

        //Then
        Assert.assertEquals(Title.class, title.getClass());
    }

}
