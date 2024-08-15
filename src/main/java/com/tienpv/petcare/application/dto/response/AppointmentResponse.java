package com.tienpv.petcare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {

    private String veterinarianName;
    private String petName;
    private String reason;
    private Integer status;
    private String appointmentDate;
}
