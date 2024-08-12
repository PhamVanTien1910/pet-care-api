package com.tienpv.petcare.infrastructure.converter.impl.admin;

import com.tienpv.petcare.application.dto.request.RoleRequest;
import com.tienpv.petcare.application.dto.response.PermissionResponse;
import com.tienpv.petcare.application.dto.response.RoleResponse;
import com.tienpv.petcare.domain.entity.auth.RoleEntity;
import com.tienpv.petcare.infrastructure.converter.admin.IPermissionConverter;
import com.tienpv.petcare.infrastructure.converter.admin.IRoleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleConverter implements IRoleConverter {
    @Autowired
    private IPermissionConverter permissionConverter;

    public RoleEntity toEntity(RoleRequest request) {
        RoleEntity entity = new RoleEntity();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return entity;
    }

    public RoleResponse toDTO(RoleEntity entity) {
        RoleResponse dto = new RoleResponse();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        Set<PermissionResponse> permissionDTO =
                entity.getPermissions().stream().map(permissionConverter::toDTO).collect(Collectors.toSet());
        dto.setPermissions(permissionDTO);
        return dto;
    }
}
