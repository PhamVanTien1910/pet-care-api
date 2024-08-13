package com.tienpv.petcare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeterinarianResponse {

    private Long id;
    private String name;
    private String specialization;
    private String contactInfo;
    private String clinicAddress;
}
