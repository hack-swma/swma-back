package com.swma.swma.domain.user.entity;


import javax.persistence.*;

import com.swma.swma.domain.user.entity.type.Language;
import com.swma.swma.domain.user.entity.type.Sex;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="user_id", unique = true, length = 8)
    private String userId;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 20) // nullable = false로 변경
    private String name;

    private int year;
    private int month;
    private int day;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column() // nullable = false로 변경
    private String img;

    @Column(length = 500) // nullable = false로 변경
    private String description;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

}
