package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class ReaderMapper {
    @Autowired
    LendingMapper lendingMapper;

    public Reader mapToReader(ReaderDto readerDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate dateOfCreatingAccountAsLocalDate = LocalDate.parse(readerDto.getDateOfCreatingAccount(), formatter);
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                dateOfCreatingAccountAsLocalDate,
                lendingMapper.mapToLendingList(readerDto.getLendings()));
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateOfCreatingAccountAsString = reader.getDateOfCreatingAccount().format(formatter);
        return new ReaderDto(
                reader.getReaderId(),
                reader.getFirstName(),
                reader.getLastName(),
                dateOfCreatingAccountAsString,
                lendingMapper.mapToLendingDtoList(reader.getLendings()));
    }
}
