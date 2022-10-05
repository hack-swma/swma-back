package com.swma.swma.domain.account.presentation;

import com.swma.swma.domain.account.presentation.dto.request.SignInRequest;
import com.swma.swma.domain.account.presentation.dto.request.SignUpRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.service.IdVerityService;
import com.swma.swma.domain.account.service.RefreshTokenService;
import com.swma.swma.domain.account.service.SignInService;
import com.swma.swma.domain.account.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final SignUpService signUpService;
    private final IdVerityService idVerityService;
    private final SignInService signInService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest request){
        TokenResponse tokenResponse =  signUpService.execute(request);
        return new ResponseEntity<>(tokenResponse,HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> idVerify(@RequestParam String userId){
        idVerityService.execute(userId);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<TokenResponse> signin(@RequestBody SignInRequest signInRequest){
        TokenResponse tokenResponse = signInService.execute(signInRequest);
        return new ResponseEntity<>(tokenResponse,HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<TokenResponse> reIssueToken(@RequestHeader("Refresh-Token") String token) {
        TokenResponse tokenData = refreshTokenService.execute(token);
        return new ResponseEntity<>(tokenData, HttpStatus.OK);
    }
}
