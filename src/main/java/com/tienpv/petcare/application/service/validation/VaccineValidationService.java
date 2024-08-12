package com.tienpv.petcare.application.service.validation;

import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class VaccineValidationService {
    public void validateVaccineRequest(VaccinationRequest request){
        if (request.getVaccineName() == null || request.getVaccineName().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getNotes() == null || request.getNotes().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getNextDueDate() == null || request.getNextDueDate().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getDateAdministered() == null || request.getDateAdministered().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
    }
}
