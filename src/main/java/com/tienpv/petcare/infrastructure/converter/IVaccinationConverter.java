package com.tienpv.petcare.infrastructure.converter;

import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;
import com.tienpv.petcare.domain.entity.VaccinationEntity;

public interface IVaccinationConverter {
    VaccinationResponse toDTO(VaccinationEntity entity);
    VaccinationEntity toEntity(VaccinationRequest request);
    VaccinationEntity toUpdateEntity(VaccinationRequest request, VaccinationEntity entity);
}
