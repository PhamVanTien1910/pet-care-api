package com.tienpv.petcare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianRequest {

    private String name;
    private String specialization;
    private String contactInfo;
    private String clinicAddress;
}
