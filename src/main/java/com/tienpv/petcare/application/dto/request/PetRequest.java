package com.tienpv.petcare.application.dto.request;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String wellness;
    private String birthdate;
}
