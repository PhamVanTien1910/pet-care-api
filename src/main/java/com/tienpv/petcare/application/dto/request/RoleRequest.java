package com.tienpv.petcare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleRequest {
    Set<String> permissions = new HashSet<>();
    private String name;
    private String description;
}