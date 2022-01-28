package com.mj.springbootbankcards.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CardSaveTo {

    @NotBlank
    @Size(min = 3)
    private String embossingName;
}
