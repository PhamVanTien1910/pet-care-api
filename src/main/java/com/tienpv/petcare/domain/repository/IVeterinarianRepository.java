package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.VeterinarianEnity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVeterinarianRepository extends JpaRepository<VeterinarianEnity, Long> {
}
