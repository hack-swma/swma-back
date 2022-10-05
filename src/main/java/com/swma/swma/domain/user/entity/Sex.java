package com.swma.swma.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sex {
    FEMALE("여성"),MALE("남성");
    private final String sex;
}
