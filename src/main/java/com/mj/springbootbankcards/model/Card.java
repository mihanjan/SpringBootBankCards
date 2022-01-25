package com.mj.springbootbankcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mj.springbootbankcards.to.CardSaveTo;
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

    private String number;

    private String contractNumber;

    private String embossingName;

    private LocalDate openDate;

    private LocalDate expireDate;

    private boolean locked;

    private LocalDate lockDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private BankCardType bankCardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Client client;

    public static Card fromCardSaveTo(CardSaveTo cardSaveTo, Client client, BankCardType bankCardType) {
        Card card = new Card();
        card.embossingName = cardSaveTo.getEmbossingName();
        card.client = client;
        card.bankCardType = bankCardType;
        card.contractNumber = String.valueOf(counterContractNumber.incrementAndGet());
        card.number = String.valueOf(counterNumber.incrementAndGet());
        card.openDate = LocalDate.now();
        card.expireDate = card.openDate.plusYears(bankCardType.getValidity());
        card.locked = false;
        return card;
    }
}
