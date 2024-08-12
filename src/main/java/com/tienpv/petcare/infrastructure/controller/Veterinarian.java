package com.tienpv.petcare.infrastructure.controller;

import com.tienpv.petcare.application.dto.response.VeterinarianResponsePage;
import com.tienpv.petcare.domain.service.IVeterinarianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/veterinarians")
public class Veterinarian {

    @Autowired
    private IVeterinarianService veterinarianService;

    @Operation(summary = "Get page veterinarian ", description = "Get page veterinarian ")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    VeterinarianResponsePage showAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit) {
        VeterinarianResponsePage result = new VeterinarianResponsePage();
        Pageable pageable = null;
        if (page != null && limit != null) {
            result.setPage(page);
            pageable = PageRequest.of(page - 1, limit);
            result.setListResult(veterinarianService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (veterinarianService.totalItem()) / limit));
        } else {
            result.setListResult(veterinarianService.findAll(pageable));
        }
        return result;
    }
}
