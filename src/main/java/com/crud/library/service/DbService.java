package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Lending;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.LendingRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbService {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LendingRepository lendingRepository;

    @Autowired
    TitleRepository titleRepository;


    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBook(final long bookId) {
        return bookRepository.findBookByBookId(bookId);
    }

    public Optional<Title> getTitle(final long titleId) {
        return titleRepository.findById(titleId);
    }

    public Optional<Reader> getReader(final long readerId) {
        return readerRepository.findByReaderId(readerId);
    }

}
