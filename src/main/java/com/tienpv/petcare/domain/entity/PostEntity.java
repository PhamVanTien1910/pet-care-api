package com.tienpv.petcare.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends BaseEntity {

    @Column
    private String content;

    @Column
    private String title;

    @OneToMany(mappedBy = "posts")
    private List<CommentEntity> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
