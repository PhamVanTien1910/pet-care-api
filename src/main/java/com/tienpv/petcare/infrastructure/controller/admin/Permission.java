package com.tienpv.petcare.infrastructure.controller.admin;

import com.tienpv.petcare.application.dto.request.PermissionRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.PermissionResponse;
import com.tienpv.petcare.domain.service.admin.IPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class Permission {

    @Autowired
    private IPermissionService permissionService;

    @Operation(summary = "Create permission", description = "Create permission")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        ApiResponse<PermissionResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(permissionService.create(request));
        return apiResponse;
    }

    @GetMapping
    @Operation(summary = "Get permission", description = "Get permission")
    @SecurityRequirement(name = "Bearer Authentication")
    ApiResponse<List<PermissionResponse>> getAll() {
        ApiResponse<List<PermissionResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(permissionService.getAll());
        return apiResponse;
    }

    @Operation(summary = "Delete permission", description = "Delete permission")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}