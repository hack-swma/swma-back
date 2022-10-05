package com.swma.swma.domain.account.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationRequest {
    private final String id;
    private final String password;
}
