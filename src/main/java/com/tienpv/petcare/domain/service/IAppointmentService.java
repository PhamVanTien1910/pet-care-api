package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;

public interface IAppointmentService {
    AppointmentResponse create(AppointmentRequest request);
}
