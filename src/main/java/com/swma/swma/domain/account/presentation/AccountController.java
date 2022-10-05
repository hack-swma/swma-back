package com.swma.swma.domain.account.presentation;

import com.swma.swma.domain.account.presentation.dto.request.SignUpRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final SignUpService signUpService;
    @PostMapping
    public ResponseEntity<TokenResponse> signup(@RequestBody SignUpRequest authenticationRequest){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
