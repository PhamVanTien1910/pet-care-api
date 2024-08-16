package com.tienpv.petcare.infrastructure.controller;

import com.tienpv.petcare.application.dto.request.AppointmentRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.AppointmentResponse;
import com.tienpv.petcare.application.dto.response.VaccinationResponse;
import com.tienpv.petcare.domain.entity.AppointmentEntity;
import com.tienpv.petcare.domain.service.IAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/appointment")
@RequiredArgsConstructor
public class appointment {

    @Autowired
    private IAppointmentService appointmentService;
    @Operation(summary = "Create appointment", description = "Create appointment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/pet/{pet_id}/veterinarian/{veterinarian_id}")
    public ApiResponse<AppointmentResponse> create(@RequestBody AppointmentRequest request,
                                                   @PathVariable("pet_id") long pet_id,
                                                   @PathVariable(value = "veterinarian_id") long veterinarian_id){
        ApiResponse<AppointmentResponse> apiResponse = new ApiResponse<>();
        request.setPetId(pet_id);
        request.setVeterinarianId(veterinarian_id);
        AppointmentResponse data = appointmentService.create(request);
        apiResponse.setResult(data);
        return apiResponse;
    }

    @Operation(summary = "Create appointment", description = "Create appointment")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/pet/{pet_id}")
    public List<AppointmentResponse> getAll(@PathVariable("pet_id") long pet_id){
        return appointmentService.getList(pet_id);
    }

}
