package com.tienpv.petcare.infrastructure.converter;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;

public interface IVeterinarianConverter {
    VeterinarianResponse toDTO(VeterinarianEnity enity);
    VeterinarianEnity toEntity(VeterinarianRequest request);
}
