package com.tienpv.petcare.domain.repository.admin;

import com.tienpv.petcare.domain.entity.auth.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
