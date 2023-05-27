package com.example.telegraph.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PostEntity extends BaseEntity {
    private String name;
    private String title;
    private String content;
    @Column(unique = true)
    private String url;
    @ManyToOne
    private UserEntity author;
}
