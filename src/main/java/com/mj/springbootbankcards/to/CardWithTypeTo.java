package com.mj.springbootbankcards.to;

import com.mj.springbootbankcards.model.BankCardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CardWithTypeTo {

    Integer id;

    private String number;

    private String contractNumber;

    private String embossingName;

    private LocalDate openDate;

    private LocalDate expireDate;

    private boolean locked;

    private LocalDate lockDate;

    private BankCardType bankCardType;
}
