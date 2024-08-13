package com.tienpv.petcare.application.service.validation;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class VeterinarinarianValidationService {
    public void validateVeterinarinarianRequest(VeterinarianRequest request){
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getSpecialization() == null || request.getSpecialization().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getClinicAddress() == null || request.getClinicAddress().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getContactInfo() == null || request.getContactInfo().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
    }
}
