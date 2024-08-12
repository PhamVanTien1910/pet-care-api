package com.tienpv.petcare.application.service.validation;

import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class PetValidationService {
   public void validatePetRequest(PetRequest request){
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getSpecies() == null || request.getSpecies().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getWellness() == null || request.getWellness().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getBreed() == null || request.getBreed().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getGender() == null || request.getGender().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getBirthdate() == null) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
    }
}
