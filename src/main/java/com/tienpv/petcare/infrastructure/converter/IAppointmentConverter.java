package com.tienpv.petcare.infrastructure.converter;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import com.tienpv.petcare.domain.entity.AppointmentEntity;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;

public interface IAppointmentConverter {
    AppointmentResponse toDTO(AppointmentEntity enity);
    AppointmentEntity toEntity(AppointmentRequest request);
}
