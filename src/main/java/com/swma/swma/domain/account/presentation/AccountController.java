package com.swma.swma.domain.account.presentation;

import com.swma.swma.domain.account.presentation.dto.request.CheckIdRequest;
import com.swma.swma.domain.account.presentation.dto.request.SignUpRequest;
import com.swma.swma.domain.account.presentation.dto.response.TokenResponse;
import com.swma.swma.domain.account.service.IdVerityService;
import com.swma.swma.domain.account.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {
    private final SignUpService signUpService;
    private final IdVerityService idVerityService;
    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signUp(@RequestBody SignUpRequest authenticationRequest){
        TokenResponse tokenResponse =  signUpService.execute(authenticationRequest);
        return new ResponseEntity<>(tokenResponse,HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> idVerify(@RequestParam String userId){
        idVerityService.execute(userId);
        return ResponseEntity.ok().build();
    }
}
