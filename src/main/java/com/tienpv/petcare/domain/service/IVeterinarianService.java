package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.request.VeterinarianRequest;
import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface IVeterinarianService {
    List<VeterinarianResponse> findAll(Pageable pageable);
    int totalItem();
    VeterinarianResponse create(VeterinarianRequest request);
}
