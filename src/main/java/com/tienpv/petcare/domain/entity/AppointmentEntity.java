package com.tienpv.petcare.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pets;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private VeterinarianEnity veterinarians;

    @Column
    private String reason;

    @Column
    private Integer status = 1;

    @Column(name = "appointment_date")
    private String appointmentDate;
}
