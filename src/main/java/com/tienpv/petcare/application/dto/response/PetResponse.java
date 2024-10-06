package com.tienpv.petcare.application.dto.response;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String wellness;
    private String birthdate;
}
