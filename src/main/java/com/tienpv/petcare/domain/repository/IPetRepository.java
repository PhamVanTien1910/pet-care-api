package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPetRepository extends JpaRepository<PetEntity, Long> {
    PetEntity findOneById(Long id);
}
