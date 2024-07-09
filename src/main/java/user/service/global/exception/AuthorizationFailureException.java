package user.service.global.exception;


import jakarta.servlet.http.HttpServletRequest;
import user.service.global.advice.ErrorCode;

public class AuthorizationFailureException extends BusinessException{
    public AuthorizationFailureException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
