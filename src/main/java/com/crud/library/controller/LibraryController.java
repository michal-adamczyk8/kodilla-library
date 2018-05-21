package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.ReaderDto;
import com.crud.library.domain.TitleDto;
import com.crud.library.mapper.Mapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    Mapper mapper;
    @Autowired
    DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        service.saveReader(mapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        service.savaeTitle(mapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        service.saveBook(mapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeStatus")
    public BookDto changeStatus(@RequestBody BookDto bookDto) throws BookNotFoundException {
        Book retrievedBook = service.getBook(bookDto.getBookId()).get();
        if (!service.getBook(bookDto.getBookId()).isPresent()) {
            throw new BookNotFoundException();
        }
        retrievedBook.setStatus(bookDto.getStatus());
        return mapper.mapToBookDto(retrievedBook);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookCount")
    public int getBookCount(TitleDto title) {
        return service.getTitleCount(mapper.mapToTitle(title));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "lendBook")
    public void lendBook(@RequestBody BookDto bookDto) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public void returnBook() {

    }



}
