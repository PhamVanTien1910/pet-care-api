package com.tienpv.petcare.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationEntity extends BaseEntity {

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "date_administered")
    private String dateAdministered;

    @Column(name = "next_due_date")
    private String nextDueDate;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pets;
}

