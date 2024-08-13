package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVeterinarianService {
    List<VeterinarianResponse> findAll(Pageable pageable);
    VeterinarianResponse create(VeterinarianRequest request);
    int totalItem();
}
