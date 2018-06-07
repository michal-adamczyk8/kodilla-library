package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.service.BookService;
import com.crud.library.service.LendingService;
import com.crud.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    BookService bookService;

    @Autowired
    LendingService lendingService;

    @Autowired
    ReaderService readerService;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerService.addingUser(readerDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        bookService.addingTitle(titleDto);
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addingBook(bookDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeStatus")
    public BookDto changeStatus(@RequestParam long bookId, String newStatus) throws BookNotFoundException {
        return lendingService.changingStatus(bookId, newStatus);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBookCount")
    public long getBookCount(TitleDto titleDto) throws TitleNotFoundException {
        return bookService.gettingBookCount(titleDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "lendBook")
    public void lendBook(@RequestBody LendingDto lendingDto) throws Exception {
        lendingService.lendBook(lendingDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public void returnBook(@RequestBody LendingDto lendingDto) throws Exception {
        lendingService.returnBook(lendingDto);

    }
}
