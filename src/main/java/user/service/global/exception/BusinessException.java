package user.service.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import user.service.global.advice.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getCode());
    }
}