package com.swma.swma.domain.user.entity;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.swma.swma.domain.user.entity.type.Sex;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private LocalDate date; // 생년월일

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column() // nullable = false로 변경
    private String img;

    @Column(length = 500) // nullable = false로 변경
    private String description;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String region;

    private LocalDate certifyDate;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Language> languages;

    @Builder
    public User(String userId, String password, String name, int year, int month, int day, Sex sex, String img, String description, String country, String region, LocalDate certifyDate,List<Language> languages) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.date = LocalDate.of(year, month, day);
        this.sex = sex;
        this.img = img;
        this.description = description;
        this.country = country;
        this.region = region;
        this.certifyDate = certifyDate;
        this.languages = languages;
    }

}
