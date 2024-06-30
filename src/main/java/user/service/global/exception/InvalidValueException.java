package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class InvalidValueException extends BusinessException{

    public InvalidValueException(String value) {
        super(value, ErrorCode.INVALID_INPUT_VALUE);
    }
}
