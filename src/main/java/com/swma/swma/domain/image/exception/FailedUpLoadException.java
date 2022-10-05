package com.swma.swma.domain.image.exception;


import com.swma.swma.global.exception.handler.ErrorCode;
import com.swma.swma.global.exception.handler.SwmaException;

public class FailedUpLoadException extends SwmaException {
    public FailedUpLoadException() {
        super(ErrorCode.FAIL_TO_UPLOAD);
    }
}
