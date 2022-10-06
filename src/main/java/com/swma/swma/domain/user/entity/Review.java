package com.swma.swma.domain.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reviewer_img")
    private String reviewerImg;
    private String message;
    private int star;
    private Long userId;
}
