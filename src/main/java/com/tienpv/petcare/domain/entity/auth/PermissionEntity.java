package com.tienpv.petcare.domain.entity.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permission")
@Getter
@Setter
public class PermissionEntity {

    @Id
    @Column(length = 191)
    private String name;

    @Column
    private String desciption;

    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();
}