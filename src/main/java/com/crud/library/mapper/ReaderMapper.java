package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {
    @Autowired
    LendingMapper lendingMapper;

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getDateOfCreatingAccount(),
                lendingMapper.mapToLendingList(readerDto.getLendings()));
    }

    public ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getDateOfCreatingAccount(),
                lendingMapper.mapToLendingDtoList(reader.getLendings()));
    }
}
