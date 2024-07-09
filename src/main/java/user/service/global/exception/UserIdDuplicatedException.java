package user.service.global.exception;

import user.service.global.advice.ErrorCode;

public class UserIdDuplicatedException extends BusinessException{
    public UserIdDuplicatedException(String message) {
        super(message, ErrorCode.USER_ID_DUPLICATED);
    }
}
