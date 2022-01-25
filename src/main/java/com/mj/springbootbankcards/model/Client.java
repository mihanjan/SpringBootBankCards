package com.mj.springbootbankcards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Client.cards",
        attributeNodes = @NamedAttributeNode("cards"))
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Client extends BaseEntity {

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Card> cards;
}
