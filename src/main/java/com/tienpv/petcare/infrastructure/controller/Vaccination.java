package com.tienpv.petcare.infrastructure.controller;

import com.tienpv.petcare.application.dto.request.VaccinationRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;
import com.tienpv.petcare.domain.service.IVaccinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class Vaccination {

    @Autowired
    private IVaccinationService vaccinationService;

    @Operation(summary = "Create vaccine", description = "Create vaccine")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/{id}/vaccine")
    ApiResponse<VaccinationResponse> create(@RequestBody VaccinationRequest request, @PathVariable("id") Long id) {
        ApiResponse<VaccinationResponse> apiResponse = new ApiResponse<>();
        request.setPetId(id);
        VaccinationResponse data = vaccinationService.create(request);
        apiResponse.setResult(data);
        return apiResponse;
    }

    @Operation(summary = "Update vaccine", description = "Update vaccine")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/{id}/vaccine/{vaccineId}")
    ApiResponse<VaccinationResponse> update(@RequestBody VaccinationRequest request,
                                            @PathVariable("id") Long id,
                                            @PathVariable("vaccineId") Long vaccineId) {
        ApiResponse<VaccinationResponse> apiResponse = new ApiResponse<>();
        request.setPetId(id);
        request.setId(vaccineId);
        VaccinationResponse data = vaccinationService.update(request);
        apiResponse.setResult(data);
        return apiResponse;
    }
}
