package com.tienpv.petcare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccinationRequest {
    private Long petId;
    private Long id;
    private String vaccineName;
    private String dateAdministered;
    private String nextDueDate;
    private String notes;
}
