package com.crud.library.mapper;

import com.crud.library.domain.Lending;
import com.crud.library.domain.LendingDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LendingMapper {
    public List<LendingDto> mapToLendingDtoList(List<Lending> lendings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lendings.stream()
                .map(lending -> new LendingDto(lending.getLendingId(), lending.getReaderId(), lending.getBookId(),
                        lending.getDateOfLending().format(formatter), lending.getDateOfReturning().format(formatter)))
                .collect(Collectors.toList());
    }

    public List<Lending> mapToLendingList(List<LendingDto> lendingDtoList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return lendingDtoList.stream()
                .map(lendingDto -> new Lending(lendingDto.getLendingId(), lendingDto.getReaderId(),
                        lendingDto.getBookId(), LocalDate.parse(lendingDto.getDateOfLending(), formatter),
                        LocalDate.parse(lendingDto.getDateOfReturning(), formatter)))
                .collect(Collectors.toList());
    }

    public Lending mapToLending(LendingDto lendingDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfLendingAsLocalDate = LocalDate.parse(lendingDto.getDateOfLending(), formatter);
        LocalDate dateOfReturningAsLocalDate = LocalDate.parse(lendingDto.getDateOfLending(), formatter);
        return new Lending(
                lendingDto.getLendingId(),
                lendingDto.getReaderId(),
                lendingDto.getBookId(),
                dateOfLendingAsLocalDate,
                dateOfReturningAsLocalDate);
    }

    public LendingDto mapToLendingDto(Lending lending) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateOfLendingAsString = lending.getDateOfLending().format(formatter);
        String dateOfReturningAsString = lending.getDateOfReturning().format(formatter);
        return new LendingDto(
                lending.getLendingId(),
                lending.getReaderId(),
                lending.getBookId(),
                dateOfLendingAsString,
                dateOfReturningAsString);
    }
}