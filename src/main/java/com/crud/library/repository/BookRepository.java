package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository  extends CrudRepository<Book, Long> {

    @Override
    Book save(Book book);



    Optional<Book> findBookByBookId(long bookId);
}
