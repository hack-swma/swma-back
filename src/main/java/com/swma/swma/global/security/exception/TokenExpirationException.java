package com.swma.swma.global.security.exception;

import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class TokenExpirationException extends SwmaException {
    public static final TokenExpirationException EXCEPTION = new TokenExpirationException();

    public TokenExpirationException() {
        super(ErrorCode.TOKEN_EXPIRATION);
    }
}
