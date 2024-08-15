package com.tienpv.petcare.application.service.validation;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class AppointmentValidationService {
    public void validateAppointmentRequest(AppointmentRequest request){
        if (request.getAppointmentDate() == null || request.getAppointmentDate().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        if (request.getReason() == null || request.getReason().isEmpty()) {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
    }

}
