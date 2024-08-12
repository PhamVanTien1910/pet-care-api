package com.tienpv.petcare.infrastructure.converter.admin;

import com.tienpv.petcare.application.dto.request.RoleRequest;
import com.tienpv.petcare.application.dto.response.RoleResponse;
import com.tienpv.petcare.domain.entity.auth.RoleEntity;


public interface IRoleConverter {
    RoleEntity toEntity(RoleRequest request);

    RoleResponse toDTO(RoleEntity entity);
}
