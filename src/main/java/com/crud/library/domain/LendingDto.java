package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LendingDto {
    private long lendingId;

    private long readerId;

    private long bookId;

    private Date dateOfLending;

    private Date dateOfReturning;
}
