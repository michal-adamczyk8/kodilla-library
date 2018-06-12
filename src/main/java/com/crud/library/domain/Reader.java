package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long readerId;

    @Column(name = "firsName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "createdOn")
    private LocalDate dateOfCreatingAccount;

    @OneToMany(
            targetEntity = Lending.class,
            mappedBy = "readerId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Lending> lendings;
}
