package com.tienpv.petcare.domain.service.admin;

import com.tienpv.petcare.application.dto.request.PermissionRequest;
import com.tienpv.petcare.application.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}