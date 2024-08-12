package com.tienpv.petcare.infrastructure.controller.admin;

import com.tienpv.petcare.application.dto.request.RoleRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.RoleResponse;
import com.tienpv.petcare.domain.service.admin.IRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class Role {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        ApiResponse<RoleResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roleService.create(request));
        return apiResponse;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    ApiResponse<List<RoleResponse>> getAll() {
        ApiResponse<List<RoleResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roleService.getAll());
        return apiResponse;
    }

    @DeleteMapping("/{role}")
    @SecurityRequirement(name = "Bearer Authentication")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
