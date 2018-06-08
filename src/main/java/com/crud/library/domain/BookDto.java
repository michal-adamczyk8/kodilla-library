package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private long bookId;

    private long titleId;

    private BookStatus status;

    public void changeStatus(BookStatus newStatus) {
        this.status = newStatus;
    }
}
