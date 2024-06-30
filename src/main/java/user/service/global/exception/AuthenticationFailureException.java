package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class AuthenticationFailureException extends BusinessException{
    public AuthenticationFailureException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
