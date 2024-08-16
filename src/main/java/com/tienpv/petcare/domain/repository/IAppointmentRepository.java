package com.tienpv.petcare.domain.repository;

import com.tienpv.petcare.domain.entity.AppointmentEntity;
import com.tienpv.petcare.domain.entity.VeterinarianEnity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    Optional<AppointmentEntity> findByVeterinarians_IdAndAppointmentDate(Long veterinarianId, String appointmentDate);
    List<AppointmentEntity> findByPets_Id(Long petId);

}
