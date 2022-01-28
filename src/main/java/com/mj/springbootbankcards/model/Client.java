package com.mj.springbootbankcards.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Client.cards", attributeNodes = @NamedAttributeNode("cards"))
@Table(name = "client")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Client extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @Column(name = "middle_name")
    @Size(min = 2, max = 100)
    private String middleName;

    @Column(name = "birth_date", nullable = false)
    @NotNull
    private LocalDate birthDate;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<Card> cards;
}
