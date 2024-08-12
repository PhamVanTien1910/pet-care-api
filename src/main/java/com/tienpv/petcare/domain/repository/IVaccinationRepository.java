package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.VaccinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVaccinationRepository extends JpaRepository<VaccinationEntity, Long> {
}
