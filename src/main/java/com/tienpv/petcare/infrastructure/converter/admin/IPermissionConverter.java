package com.tienpv.petcare.infrastructure.converter.admin;

import com.tienpv.petcare.application.dto.request.PermissionRequest;
import com.tienpv.petcare.application.dto.response.PermissionResponse;
import com.tienpv.petcare.domain.entity.auth.PermissionEntity;

public interface IPermissionConverter {
    PermissionEntity toEntity(PermissionRequest dto);

    PermissionResponse toDTO(PermissionEntity entity);
}
