package com.mj.springbootbankcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name = "card")
@NamedEntityGraph(name = "Card.bankCardType", attributeNodes = @NamedAttributeNode("bankCardType"))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"bankCardType", "client"})
public class Card extends BaseEntity {

    public static final AtomicLong counterContractNumber = new AtomicLong(10000L);
    public static final AtomicLong counterNumber = new AtomicLong(1000000000000000L);

    @Column(name = "number", nullable = false, unique = true)
    @NotBlank
    @Size(min = 12, max = 20)
    private String number;

    @Column(name = "contract_number", nullable = false, unique = true)
    @NotBlank
    private String contractNumber;

    @Column(name = "embossing_name", nullable = false)
    @NotBlank
    @Size(min = 3)
    private String embossingName;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate = LocalDate.now();

    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Column(name = "locked")
    private boolean locked = false;

    @Column(name = "lock_date")
    private LocalDate lockDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_card_type_id")
    private BankCardType bankCardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;
}
