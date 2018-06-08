package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @ManyToOne(targetEntity = Title.class)
    @JoinColumn(name = "titleId")
    private long titleId;

    @Column(name = "status")
    private BookStatus status;





}
