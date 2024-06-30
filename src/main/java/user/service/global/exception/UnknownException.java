package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class UnknownException extends BusinessException{
	public UnknownException(String message) {
        super(message, ErrorCode.UNKNOWN_ERROR);
    }
}
