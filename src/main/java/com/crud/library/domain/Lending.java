package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "lendings")
public class Lending {

    @ManyToOne
    @JoinColumn(name = "readerId")
    private long readerId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookId")
    private long bookId;

    @Column(name = "dateOfLending")
    private Date dateOfLending;

    @Column(name = "dateOfReturning")
    private Date dateOfReturning;
}
