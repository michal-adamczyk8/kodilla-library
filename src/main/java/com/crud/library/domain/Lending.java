package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity(name = "lendings")
public class Lending {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lendingId;

    @ManyToOne(targetEntity = Reader.class)
    @JoinColumn(name = "readerId")
    private long readerId;

    @OneToOne(targetEntity = Book.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private long bookId;

    @Column(name = "dateOfLending")
    private LocalDate dateOfLending;

    @Column(name = "dateOfReturning")
    private LocalDate dateOfReturning;
}
