package com.tienpv.petcare.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private String species;

    @Column
    private String breed;

    @Column
    private String gender;

    @Column
    private String wellness;

    @Column
    private String birthdate;

    @OneToMany(mappedBy = "pets")
    private List<VaccinationEntity> vaccinations = new ArrayList<>();

    @OneToMany(mappedBy = "pets")
    private List<AppointmentEntity> appointments = new ArrayList<>();

}

