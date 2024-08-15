package com.tienpv.petcare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequest {
    private Long petId;
    private Long veterinarianId;
    private String reason;
    private String appointmentDate;
}
