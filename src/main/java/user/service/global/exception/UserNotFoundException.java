package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(String message) {
        super(message, ErrorCode.USER_NOT_FOUND);
    }

}
