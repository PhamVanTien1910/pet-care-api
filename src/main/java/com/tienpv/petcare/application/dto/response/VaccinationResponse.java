package com.tienpv.petcare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinationResponse {
    private String vaccineName;
    private String dateAdministered;
    private String nextDueDate;
    private String notes;
    private String petName;
}
