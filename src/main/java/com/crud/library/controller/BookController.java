package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.TitleDto;
import com.crud.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "/title", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        bookService.addingTitle(titleDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/book", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addingBook(bookDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bookCount")
    public long getBookCount(@RequestParam long titleId) {
        return bookService.gettingBookCount(titleId);
    }
}
