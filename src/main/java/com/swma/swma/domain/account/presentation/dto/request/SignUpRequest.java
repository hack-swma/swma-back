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

    @NotBlank(message = "아이디를 입력해주세요")
    @Size(max = 8, min = 2)
    private final String id;

    @NotBlank(message = "비밀번호(필수)")
    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호에 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private final String password;

    @NotBlank
    @Size(min = 2, max = 20)
    private final String name;

    private final int year;
    private final int month;
    private final int day;

    @NotBlank(message = "성별을 입력해주세요")
    private final Sex sex;

    @NotBlank(message = "프로필 사진을 업로드해주세요")
    private final String img;

    @NotBlank
    @Size(min = 20, max = 500, message = "자기소개를 입력해주세요")
    private final String description;

    private final List<LanguageType> language;

    @NotBlank
    private final String country;

    @NotBlank
    private final String region;

    public LocalDate validDate() {
        LocalDate date = LocalDate.of(year, month, day);
        if(date.isAfter(date)) {
            throw  DateBadRequestException.Exception;
        }
        return date;
    }
}
