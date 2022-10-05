package com.swma.swma.domain.account.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SignInRequest {
    @NotBlank(message = "이메일(필수)")
    private final String id;
    @NotBlank(message = "비밀번호(필수)")
    private final String password;
}
