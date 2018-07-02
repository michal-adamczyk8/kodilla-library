package com.crud.library.service;

import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.TitleDto;
import com.crud.library.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    DbService dbService;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    TitleMapper titleMapper;


    public void addingTitle(TitleDto titleDto) {
        dbService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    public void addingBook(BookDto bookDto) {
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    public long gettingBookCount(long titleId) {
        try {
            if (!dbService.getTitle(titleId).isPresent()){
                throw new TitleNotFoundException();
            }
            long result = dbService.getTitle(titleId).get().getBooks()
                    .stream()
                    .filter(p->p.getStatus().toString().equals("available"))
                    .count();
            return result;
        } catch (TitleNotFoundException e) {
            System.out.println(e);
        }
        return -1L;
    }

    //TODO : dodać metode ktora wyswietli liste ksiazek, egzemplarzy





}
