package com.swma.swma.domain.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,name="user_id",unique = true)
    private String userId;
    @Column(length = 60,nullable = false)
    private String password;
    private String name;
    private int year;
    private int month;
    private int day;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private int period;
    private String region;
    private String img;
    private String description;
    private String refreshToken;
}
