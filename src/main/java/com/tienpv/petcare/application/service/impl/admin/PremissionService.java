package com.tienpv.petcare.application.service.impl.admin;

import com.tienpv.petcare.application.dto.request.PermissionRequest;
import com.tienpv.petcare.application.dto.response.PermissionResponse;
import com.tienpv.petcare.domain.entity.auth.PermissionEntity;
import com.tienpv.petcare.domain.repository.admin.IPermissionRepository;
import com.tienpv.petcare.domain.service.admin.IPermissionService;
import com.tienpv.petcare.infrastructure.converter.admin.IPermissionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PremissionService implements IPermissionService {

    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IPermissionConverter converter;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public PermissionResponse create(PermissionRequest request) {
        PermissionEntity permissionEntity = converter.toEntity(request);
        permissionEntity = permissionRepository.save(permissionEntity);
        return converter.toDTO(permissionEntity);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<PermissionResponse> getAll() {
        List<PermissionEntity> permissions = permissionRepository.findAll();
        return permissions.stream().map(converter::toDTO).toList();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
