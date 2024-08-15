package com.tienpv.petcare.application.service.impl;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import com.tienpv.petcare.application.service.validation.AppointmentValidationService;
import com.tienpv.petcare.domain.entity.AppointmentEntity;
import com.tienpv.petcare.domain.entity.PetEntity;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;
import com.tienpv.petcare.domain.repository.IAppointmentRepository;
import com.tienpv.petcare.domain.repository.IPetRepository;
import com.tienpv.petcare.domain.repository.IVeterinarianRepository;
import com.tienpv.petcare.domain.service.IAppointmentService;
import com.tienpv.petcare.infrastructure.converter.IAppointmentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentConverter appointmentConverter;
    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IPetRepository petRepository;

    @Autowired
    private IVeterinarianRepository veterinarianRepository;

    @Autowired
    private AppointmentValidationService appointmentValidationService;

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        appointmentValidationService.validateAppointmentRequest(request);
        AppointmentEntity entity = new AppointmentEntity();
        PetEntity petEntity = new PetEntity();
        VeterinarianEnity veterinarianEnity = new VeterinarianEnity();
        entity = appointmentConverter.toEntity(request);
        if (request.getPetId() != null) {
            petEntity = petRepository.findOneById(request.getPetId());
            veterinarianEnity = veterinarianRepository.findOneById(request.getVeterinarianId());
            if (petEntity == null){
                throw new AppException(ErrorCode.ID_NOT_EXISTED);
            }
            if (veterinarianEnity == null){
                throw new AppException(ErrorCode.ID_NOT_EXISTED);
            }
            entity.setPets(petEntity);
            entity.setVeterinarians(veterinarianEnity);
            entity = appointmentRepository.save(entity);
        }
        AppointmentResponse response;
        response = appointmentConverter.toDTO(entity);
        response.setPetName(petEntity.getName());
        response.setVeterinarianName(veterinarianEnity.getName());
        return response;
    }
}
