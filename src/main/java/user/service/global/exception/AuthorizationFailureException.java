package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class AuthorizationFailureException extends BusinessException{
    public AuthorizationFailureException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
