package com.tienpv.petcare.application.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PetRequest {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String wellness;
    private String birthdate;
}
