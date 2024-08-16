package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.domain.entity.AppointmentEntity;

import java.util.List;

public interface IAppointmentService {
    AppointmentResponse create(AppointmentRequest request);
    List<AppointmentResponse> getList(Long request);
}
