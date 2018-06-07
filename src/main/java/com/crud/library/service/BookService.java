package com.crud.library.service;

import com.crud.library.controller.TitleNotFoundException;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.TitleDto;
import com.crud.library.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    DbService dbService;
    @Autowired
    Mapper mapper;


    public void addingTitle(TitleDto titleDto) {
        dbService.saveTitle(mapper.mapToTitle(titleDto));
    }

    public void addingBook(BookDto bookDto) {
        dbService.saveBook(mapper.mapToBook(bookDto));
    }

    public long gettingBookCount(TitleDto titleDto) throws TitleNotFoundException {
        if (!dbService.getTitle(titleDto.getTitleId()).isPresent()){
            throw new TitleNotFoundException();
        }
        long result = dbService.getTitle(titleDto.getTitleId()).get().getBooks()
                .stream()
                .filter(p->p.getStatus() == "available")
                .count();
        return result;
    }





}
