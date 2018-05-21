package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "titles")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long titleId;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publishedOn")
    private long yearOfPublish;

    @OneToMany(
            targetEntity = Book.class,
            mappedBy = "titleId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    private List<Book> books = new ArrayList<>();
}
