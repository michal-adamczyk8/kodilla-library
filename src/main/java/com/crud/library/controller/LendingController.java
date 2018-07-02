package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.LendingDto;
import com.crud.library.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/library")
public class LendingController {
    @Autowired
    LendingService lendingService;

    @RequestMapping(method = RequestMethod.PUT, value = "lending")
    public void lendBook(@RequestBody LendingDto lendingDto) {
        lendingService.lendBook(lendingDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "lendingBack")
    public void returnBook(@RequestBody LendingDto lendingDto) {
        lendingService.returnBook(lendingDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "status")
    public BookDto changeStatus(@RequestParam long bookId, @RequestParam BookStatus newStatus) {
        return lendingService.changingStatus(bookId, newStatus);
    }
}
