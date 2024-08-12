package com.tienpv.petcare.domain.service;


import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;

public interface IVaccinationService {
    VaccinationResponse create(VaccinationRequest request);
    VaccinationResponse update(VaccinationRequest request);
}
