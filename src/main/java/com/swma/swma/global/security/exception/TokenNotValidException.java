package com.swma.swma.global.security.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class TokenNotValidException extends SwmaException {
    public static final TokenNotValidException EXCEPTION = new TokenNotValidException();

    public TokenNotValidException() {
        super(ErrorCode.TOKEN_NOT_VALID);
    }
}
