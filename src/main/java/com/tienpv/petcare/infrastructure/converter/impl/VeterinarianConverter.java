package com.tienpv.petcare.infrastructure.converter.impl;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;
import com.tienpv.petcare.infrastructure.converter.IVeterinarianConverter;
import org.springframework.stereotype.Component;

@Component
public class VeterinarianConverter implements IVeterinarianConverter {

    @Override
    public VeterinarianResponse toDTO(VeterinarianEnity enity) {
        VeterinarianResponse response = new VeterinarianResponse();
        response.setClinicAddress(enity.getClinicAddress());
        response.setName(enity.getName());
        response.setContactInfo(enity.getContactInfo());
        response.setSpecialization(enity.getSpecialization());
        return response;
    }

    @Override
    public VeterinarianEnity toEntity(VeterinarianRequest request) {
        return null;
    }

}
