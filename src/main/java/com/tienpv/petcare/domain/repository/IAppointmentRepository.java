package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}
