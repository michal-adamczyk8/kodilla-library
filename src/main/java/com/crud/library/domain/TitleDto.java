package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TitleDto {

    private long titleId;

    private String title;

    private String author;

    private long yearOfPublish;

    private List<Book> books = new ArrayList<>();


}
