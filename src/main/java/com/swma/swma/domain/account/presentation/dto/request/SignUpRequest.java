package com.swma.swma.domain.account.presentation.dto.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.swma.swma.domain.user.entity.type.LanguageType;
import com.swma.swma.domain.user.entity.type.Sex;
import com.swma.swma.global.exception.DateBadRequestException;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "이메일(필수)")
    private final String id;
    @NotBlank(message = "비밀번호(필수)")
    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,40}",
            message = "비밀번호에 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 40자의 비밀번호여야 합니다.")
    private final String password;

    @NotBlank
    @Size(min = 8, max = 20)
    private String name;

    private int year;
    private int month;
    private int day;

    @NotBlank(message = "성별을 입력해주세요")
    private Sex sex;

    @NotBlank
    private String img;

    @NotBlank
    @Size(min = 20, max = 500)
    private String description;

    @NotBlank
    private List<LanguageType> language;

    @NotBlank
    private String country;

    @NotBlank
    private String region;

    public LocalDate validDate() {
        LocalDate date = LocalDate.of(year, month, day);
        if(date.isAfter(date)) {
            throw  DateBadRequestException.Exception;
        }
        return date;
    }
}
