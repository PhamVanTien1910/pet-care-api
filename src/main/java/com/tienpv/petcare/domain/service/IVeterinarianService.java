package com.tienpv.petcare.domain.service;

import com.tienpv.petcare.application.dto.response.VeterinarianResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVeterinarianService {
    List<VeterinarianResponse> findAll(Pageable pageable);
    int totalItem();
}
