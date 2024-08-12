package com.tienpv.petcare.infrastructure.converter.impl;

import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.dto.response.PetResponse;
import com.tienpv.petcare.domain.entity.PetEntity;
import com.tienpv.petcare.infrastructure.converter.IPetConverter;
import org.springframework.stereotype.Component;

@Component
public class PetConverter implements IPetConverter {
    @Override
    public PetResponse toDTO(PetEntity entity) {
        PetResponse response = new PetResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setBirthdate(entity.getBirthdate());
        response.setGender(entity.getGender());
        response.setBreed(entity.getBreed());
        response.setWellness(entity.getWellness());
        response.setSpecies(entity.getSpecies());
        return response;
    }

    @Override
    public PetEntity toEntity(PetRequest request) {
        PetEntity entity = new PetEntity();
        entity.setName(request.getName());
        entity.setGender(request.getGender());
        entity.setBirthdate(request.getBirthdate());
        entity.setBreed(request.getBreed());
        entity.setWellness(request.getWellness());
        entity.setSpecies(request.getSpecies());
        return entity;
    }

    @Override
    public PetEntity toUpdateEntity(PetRequest request, PetEntity entity) {
        entity.setName(request.getName());
        entity.setGender(request.getGender());
        entity.setBirthdate(request.getBirthdate());
        entity.setBreed(request.getBreed());
        entity.setWellness(request.getWellness());
        entity.setSpecies(request.getSpecies());
        return entity;
    }
}
