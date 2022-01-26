package com.mj.springbootbankcards.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientTo {

    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    private LocalDate birthDate;
}
