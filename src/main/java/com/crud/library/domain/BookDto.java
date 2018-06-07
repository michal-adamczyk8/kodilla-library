package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class BookDto {

    private long bookId;

    private long titleId;

    private String status;

    public void changeStatus(String newStatus) {
        this.status = newStatus;
    }
}
