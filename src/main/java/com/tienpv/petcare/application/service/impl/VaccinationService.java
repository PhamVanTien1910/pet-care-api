package com.tienpv.petcare.application.service.impl;

import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import com.tienpv.petcare.application.service.validation.VaccineValidationService;
import com.tienpv.petcare.domain.entity.PetEntity;
import com.tienpv.petcare.domain.entity.VaccinationEntity;
import com.tienpv.petcare.domain.repository.IPetRepository;
import com.tienpv.petcare.domain.repository.IVaccinationRepository;
import com.tienpv.petcare.domain.service.IVaccinationService;
import com.tienpv.petcare.infrastructure.converter.IVaccinationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationService implements IVaccinationService {

    @Autowired
    private IVaccinationConverter vaccinationConverter;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IVaccinationRepository vaccinationRepository;

    @Autowired
    private VaccineValidationService validationService;

    @Override
    public VaccinationResponse create(VaccinationRequest request) {
        validationService.validateVaccineRequest(request);
        VaccinationEntity entity = new VaccinationEntity();
        PetEntity petEntity = new PetEntity();
        entity = vaccinationConverter.toEntity(request);
        if (request.getPetId() != null){
            petEntity = petRepository.findOneById(request.getPetId());
            if( petEntity == null) {
                throw new AppException(ErrorCode.ID_NOT_EXISTED);
            }
            entity.setPets(petEntity);
            entity = vaccinationRepository.save(entity);
        }
        VaccinationResponse response;
        response = vaccinationConverter.toDTO(entity);
        response.setPetName(petEntity.getName());
        return response;
    }

    @Override
    public VaccinationResponse update(VaccinationRequest request) {
        VaccinationEntity entity = new VaccinationEntity();
        PetEntity petEntity = new PetEntity();
        entity = vaccinationConverter.toEntity(request);
        if (request.getPetId() != null){
            petEntity = petRepository.findOneById(request.getPetId());
            if( petEntity == null) {
                throw new AppException(ErrorCode.ID_NOT_EXISTED);
            }
            if(request.getId() != null){
             VaccinationEntity oldentity = vaccinationRepository.findById(request.getId())
                                              .orElseThrow(() -> new AppException(ErrorCode.ID_NOT_EXISTED));
             oldentity.setPets(petEntity);
             entity = vaccinationConverter.toUpdateEntity(request, oldentity);
            }
            entity = vaccinationRepository.save(entity);
        }
        VaccinationResponse response;
        response = vaccinationConverter.toDTO(entity);
        response.setPetName(petEntity.getName());
        return response;
    }
}
