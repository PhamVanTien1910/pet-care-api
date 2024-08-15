package com.tienpv.petcare.infrastructure.converter.impl;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.domain.entity.AppointmentEntity;
import com.tienpv.petcare.infrastructure.converter.IAppointmentConverter;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter implements IAppointmentConverter {
    @Override
    public AppointmentResponse toDTO(AppointmentEntity enity) {
        AppointmentResponse response = new AppointmentResponse();
        response.setAppointmentDate(enity.getAppointmentDate());
        response.setStatus(enity.getStatus());
        response.setReason(enity.getReason());
        return response;
    }

    @Override
    public AppointmentEntity toEntity(AppointmentRequest request) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setAppointmentDate(request.getAppointmentDate());
        entity.setReason(request.getReason());
        return entity;
    }
}
