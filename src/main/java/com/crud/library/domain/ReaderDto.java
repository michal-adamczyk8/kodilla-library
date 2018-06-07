package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class ReaderDto {

    private long readerId;

    private String firstName;

    private String lastName;

    private Date dateOfCreatingAccount;

    private List<LendingDto> lendings;

}
