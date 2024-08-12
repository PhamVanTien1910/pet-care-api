package com.tienpv.petcare.domain.repository.admin;

import com.tienpv.petcare.domain.entity.auth.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, String> {
}
