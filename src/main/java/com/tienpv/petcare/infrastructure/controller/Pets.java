package com.tienpv.petcare.infrastructure.controller;

import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.PetResponse;
import com.tienpv.petcare.domain.service.IPetSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class Pets {

    @Autowired
    private IPetSerivce petSerivce;

    @Operation(summary = "Create pets", description = "Create pets")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    ApiResponse<PetResponse> create(@RequestBody PetRequest request) {
        ApiResponse<PetResponse> apiResponse = new ApiResponse<>();
        PetResponse data = petSerivce.createPet(request);
        apiResponse.setResult(data);
        return apiResponse;
    }

    @Operation(summary = "Update pets", description = "Update pets")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(value = "/{id}")
    ApiResponse<PetResponse> update(@RequestBody PetRequest request, @PathVariable("id") long id) {
        ApiResponse<PetResponse> apiResponse = new ApiResponse<>();
        request.setId(id);
        PetResponse data = petSerivce.updatePet(request);
        apiResponse.setResult(data);
        return apiResponse;
    }

}
