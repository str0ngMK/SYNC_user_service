package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class IdenticalValuesCannotChangedException extends BusinessException{
	public IdenticalValuesCannotChangedException(String value) {
		super(value, ErrorCode.IDENTICAL_VALUE);
	}
}
