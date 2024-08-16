package com.tienpv.petcare.application.service.impl;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        }
        if (request.getVeterinarianId() != null && request.getAppointmentDate() != null) {
            Optional<AppointmentEntity> existingAppointment = appointmentRepository.findByVeterinarians_IdAndAppointmentDate(
                    request.getVeterinarianId(),
                    request.getAppointmentDate()
            );

            if (existingAppointment.isPresent()) {
                throw new AppException(ErrorCode.APPOINTMENT_CONFLICT);
            }
        }
        entity.setPets(petEntity);
        entity.setVeterinarians(veterinarianEnity);
        entity = appointmentRepository.save(entity);
        AppointmentResponse response;
        response = appointmentConverter.toDTO(entity);
        response.setPetName(petEntity.getName());
        response.setVeterinarianName(veterinarianEnity.getName());
        return response;
    }

    @Override
    public List<AppointmentResponse> getList(Long request) {
        List<AppointmentResponse> results = new ArrayList<>();
        if(request != null){
            List<AppointmentEntity> entities = appointmentRepository.findByPets_Id(request);
            if(entities == null){
                throw new AppException(ErrorCode.ID_NOT_EXISTED);
            }
            for (AppointmentEntity item : entities){
                AppointmentResponse response = appointmentConverter.toDTO(item);
                response.setPetName(item.getPets().getName());
                response.setVeterinarianName(item.getVeterinarians().getName());
                results.add(response);
            }
        }else {
            throw new AppException(ErrorCode.INFO_INVALID);
        }
        return results;
    }
}
