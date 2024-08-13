package com.tienpv.petcare.application.service.impl;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import com.tienpv.petcare.application.service.validation.VeterinarinarianValidationService;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;
import com.tienpv.petcare.domain.repository.IVeterinarianRepository;
import com.tienpv.petcare.domain.service.IVeterinarianService;
import com.tienpv.petcare.infrastructure.converter.IVeterinarianConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeterinarianService implements IVeterinarianService {

    @Autowired
    private IVeterinarianRepository veterinarianRepository;

    @Autowired
    private IVeterinarianConverter veterinarianConverter;

    @Autowired
    private VeterinarinarianValidationService validationService;
    @Override
    public List<VeterinarianResponse> findAll(Pageable pageable) {
        List<VeterinarianResponse> results = new ArrayList<>();
        List<VeterinarianEnity> entities = veterinarianRepository.findAll(pageable).getContent();
        for (VeterinarianEnity item : entities){
             VeterinarianResponse response = veterinarianConverter.toDTO(item);
             results.add(response);
        }
        return results;
    }

    @Override
    public int totalItem() {
        return (int) veterinarianRepository.count();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public VeterinarianResponse create(VeterinarianRequest request) {
        validationService.validateVeterinarinarianRequest(request);
        VeterinarianEnity enity = new VeterinarianEnity();
        enity = veterinarianConverter.toEntity(request);
        enity = veterinarianRepository.save(enity);
        enity.setId(enity.getId());
        return veterinarianConverter.toDTO(enity);
    }
}
