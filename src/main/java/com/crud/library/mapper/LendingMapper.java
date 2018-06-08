package com.crud.library.mapper;

import com.crud.library.domain.Lending;
import com.crud.library.domain.LendingDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LendingMapper {
    public List<LendingDto> mapToLendingDtoList(List<Lending> lendings) {
        return lendings.stream()
                .map(lending -> new LendingDto(lending.getLendingId(), lending.getReaderId(), lending.getBookId(),
                        lending.getDateOfLending(), lending.getDateOfReturning()))
                .collect(Collectors.toList());
    }

    public List<Lending> mapToLendingList(List<LendingDto> lendingDtoList) {
        return lendingDtoList.stream()
                .map(lendingDto -> new Lending(lendingDto.getLendingId(), lendingDto.getReaderId(),
                        lendingDto.getBookId(), lendingDto.getDateOfLending(), lendingDto.getDateOfReturning()))
                .collect(Collectors.toList());
    }

    public Lending mapToLending(LendingDto lendingDto) {
        return new Lending(
                lendingDto.getLendingId(),
                lendingDto.getReaderId(),
                lendingDto.getBookId(),
                lendingDto.getDateOfLending(),
                lendingDto.getDateOfReturning());
    }

    public LendingDto mapToLendingDto(Lending lending) {
        return new LendingDto(
                lending.getLendingId(),
                lending.getReaderId(),
                lending.getBookId(),
                lending.getDateOfLending(),
                lending.getDateOfReturning());
    }
}