package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);

    boolean existsByUserName(String username);
}
