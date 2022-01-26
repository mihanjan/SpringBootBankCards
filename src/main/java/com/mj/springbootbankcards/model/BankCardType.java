package com.mj.springbootbankcards.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bankcardtype")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true)
public class BankCardType extends BaseEntity {

    @Column(name = "embossing_name_length")
    private Integer embossingNameLength;

    @Column(name = "payment_system_type")
    @Enumerated(EnumType.STRING)
    private PaymentSystemType paymentSystemType;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "frequency_of_service_charges")
    private Integer frequencyOfServiceCharges;

    @Column(name = "interest_free_period")
    private Integer interestFreePeriod; // in days

    @Column(name = "interest_rate")
    private Integer interestRate;

    @Column(name = "validity")
    private Integer validity; // in years

    @Column(name = "credit_limit")
    private Integer creditLimit;
}
