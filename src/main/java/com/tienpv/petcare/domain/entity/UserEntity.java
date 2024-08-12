package com.tienpv.petcare.domain.entity;

import com.tienpv.petcare.domain.entity.auth.RoleEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Column(name = "username")
    private String userName;

    @Column
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts = new ArrayList<>();
}
