package com.tienpv.petcare.infrastructure.converter.impl;

import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;
import com.tienpv.petcare.domain.entity.VaccinationEntity;
import com.tienpv.petcare.infrastructure.converter.IVaccinationConverter;
import org.springframework.stereotype.Component;

@Component
public class VaccinationConverter implements IVaccinationConverter {
    @Override
    public VaccinationEntity toUpdateEntity(VaccinationRequest request, VaccinationEntity entity) {
        entity.setVaccineName(request.getVaccineName());
        entity.setNotes(request.getNotes());
        entity.setDateAdministered(request.getDateAdministered());
        entity.setNextDueDate(request.getNextDueDate());
        return entity;
    }

    @Override
    public VaccinationResponse toDTO(VaccinationEntity entity) {
        VaccinationResponse response = new VaccinationResponse();
        response.setNotes(entity.getNotes());
        response.setNextDueDate(entity.getNextDueDate());
        response.setVaccineName(entity.getVaccineName());
        response.setDateAdministered(entity.getDateAdministered());
        return response;
    }

    @Override
    public VaccinationEntity toEntity(VaccinationRequest request) {
        VaccinationEntity entity = new VaccinationEntity();
        entity.setVaccineName(request.getVaccineName());
        entity.setNotes(request.getNotes());
        entity.setDateAdministered(request.getDateAdministered());
        entity.setNextDueDate(request.getNextDueDate());
        return entity;
    }
}
