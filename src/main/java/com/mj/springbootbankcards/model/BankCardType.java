package com.mj.springbootbankcards.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "bankcardtype")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class BankCardType extends BaseEntity {

    private int embossingNameLength;

    @Enumerated(EnumType.STRING)
    private PaymentSystemType paymentSystemType;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private Integer frequencyOfServiceCharges;

    private Integer interestFreePeriod; // in days

    private Integer interestRate;

    private Integer validity; // in years

    private Integer creditLimit;
}
