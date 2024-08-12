package com.tienpv.petcare.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleResponse {
    Set<PermissionResponse> permissions = new HashSet<>();
    private String name;
    private String description;
}
