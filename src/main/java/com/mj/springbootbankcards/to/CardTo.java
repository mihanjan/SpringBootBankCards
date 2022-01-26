package com.mj.springbootbankcards.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CardTo {

    Integer id;

    private String number;

    private String contractNumber;

    private String embossingName;

    private LocalDate openDate;

    private LocalDate expireDate;

    private boolean locked;

    private LocalDate lockDate;
}
