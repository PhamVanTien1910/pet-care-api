package com.tienpv.petcare.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeterinarianEnity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String specialization;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "clinic_address")
    private String clinicAddress;

    @OneToMany(mappedBy = "veterinarians")
    private List<AppointmentEntity> appointment = new ArrayList<>();
}
