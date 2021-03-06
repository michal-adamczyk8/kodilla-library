package com.crud.library.mapper;

import com.crud.library.domain.Lending;
import com.crud.library.domain.LendingDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LendingMapperTestSuite {
    @InjectMocks
    LendingMapper lendingMapper;

    @Test
    public void shouldMapToLendingList() {
        //Given
        LendingDto lendingDto1 = new LendingDto(1L, 1L, 1L,
                "2017-05-05", "2017-08-19");
        LendingDto lendingDto2 = new LendingDto(2l, 3L, 11L,
                "2015-05-25", "2018-12-12");
        LendingDto lendingDto3 = new LendingDto(3l, 124L, 11L,
                "2018-11-05", "2018-12-31");

        Lending lending1 = new Lending(1L, 1L, 1L,
                LocalDate.of(2017, 5, 5), LocalDate.of(2017, 8, 19));
        Lending lending2 = new Lending(2L, 3L, 11L,
                LocalDate.of(2015, 05, 25), LocalDate.of(2018, 12, 12));
        Lending lending3 = new Lending(3L, 124L, 11L,
                LocalDate.of(2018, 11, 05), LocalDate.of(2018, 12, 31));

        List<LendingDto> lendingDtoList = Arrays.asList(lendingDto1, lendingDto2, lendingDto3);

        //When
        List<Lending> lendingsList = lendingMapper.mapToLendingList(lendingDtoList);

        //Then
        Assert.assertEquals(3, lendingsList.size());
        Assert.assertEquals(lending1.getBookId(), lendingDto1.getBookId());
        Assert.assertEquals(lending1.getLendingId(), lendingDto1.getLendingId());
        Assert.assertEquals(lending1.getReaderId(), lendingDto1.getReaderId());
        Assert.assertEquals(lending2.getBookId(), lendingDto2.getBookId());
        Assert.assertEquals(lending2.getLendingId(), lendingDto2.getLendingId());
        Assert.assertEquals(lending2.getReaderId(), lendingDto2.getReaderId());
        Assert.assertEquals(lending3.getBookId(), lendingDto3.getBookId());
        Assert.assertEquals(lending3.getLendingId(), lendingDto3.getLendingId());
        Assert.assertEquals(lending3.getReaderId(), lendingDto3.getReaderId());
    }

    @Test
    public void shouldMapToLendingDtoList() {
        //Given
        LendingDto lendingDto1 = new LendingDto(1L, 1L, 1L,
                "2017-05-05", "2017-08-19");
        LendingDto lendingDto2 = new LendingDto(2l, 3l, 11L,
                "2015-05-25", "2018-12-12");

        Lending lending1 = new Lending(1L, 1L, 1L,
                LocalDate.of(2017, 5, 5), LocalDate.of(2017, 8, 19));
        Lending lending2 = new Lending(2L, 3L, 11L,
                LocalDate.of(2015, 5, 25), LocalDate.of(2018, 12, 12));

        List<Lending> lendingList = Arrays.asList(lending1, lending2);

        //When
        List<LendingDto> lendingDtoList = lendingMapper.mapToLendingDtoList(lendingList);

        //Then
        Assert.assertEquals(2, lendingDtoList.size());
        Assert.assertEquals(lendingDto1.getBookId(), lending1.getBookId());
        Assert.assertEquals(lendingDto1.getBookId(), lending1.getLendingId());

        Assert.assertEquals(lendingDto2.getBookId(), lending2.getBookId());
        Assert.assertEquals(lendingDto2.getLendingId(), lending2.getLendingId());
    }



    @Test
    public void shouldMapToLending() {
        //Given
        LendingDto lendingDto = new LendingDto(1L, 1L, 1L,
                "2018-01-30", "2018-3-24");

        //When
        Lending lending = lendingMapper.mapToLending(lendingDto);

        //Then
        Assert.assertEquals(Lending.class, lending.getClass());
    }

    @Test
    public void shouldMapToLendingDto() {
        //Given
        Lending lending = new Lending(1L, 1L, 1L,
                LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 2));

        //When
        LendingDto lendingDto = lendingMapper.mapToLendingDto(lending);

        //Then
        Assert.assertEquals(LendingDto.class, lendingDto.getClass());
    }
}
