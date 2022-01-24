package com.mj.springbootbankcards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Card extends BaseEntity {

    private static final AtomicLong counterContractNumber = new AtomicLong(10000L);
    private static final AtomicLong counterNumber = new AtomicLong(1000000000000000L);

    private LocalDate expireDate;

    private LocalDate openDate;

    private String contractNumber;

    private boolean locked;

    private String number;

    @ManyToOne(fetch = FetchType.EAGER)
    private BankCardType bankCardType;

    private String embossingName;

    private LocalDate lockDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Client client;

//    public Card(Client client, BankCardType bankCardType, String embossingName) {
//        this.client = client;
//        this.bankCardType = bankCardType;
//        this.embossingName = embossingName;
//        this.contractNumber = String.valueOf(counterContractNumber.incrementAndGet());
//        this.number = String.valueOf(counterNumber.incrementAndGet());
//        this.openDate = LocalDate.now();
//        this.expireDate = this.openDate.plusYears(bankCardType.getValidity());
//        this.locked = false;
//    }
}
