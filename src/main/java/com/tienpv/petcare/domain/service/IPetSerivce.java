package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.dto.response.PetResponse;

public interface IPetSerivce {
    PetResponse createPet(PetRequest request);

    PetResponse updatePet(PetRequest request);
}
