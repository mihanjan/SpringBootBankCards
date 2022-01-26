package com.mj.springbootbankcards.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClientWithCardsTo {

    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate birthDate;

    private List<CardTo> cards;
}
