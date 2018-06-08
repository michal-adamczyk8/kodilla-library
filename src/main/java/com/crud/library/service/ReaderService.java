package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    DbService dbService;
    @Autowired
    ReaderMapper readerMapper;

    public void addingUser(ReaderDto readerDto) {
        dbService.saveReader(readerMapper.mapToReader(readerDto));
    }

    public Optional<Reader> getReader(long readerId) {
        return dbService.getReader(readerId);
    }

}
