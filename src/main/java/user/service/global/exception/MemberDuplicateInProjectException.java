package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class MemberDuplicateInProjectException extends BusinessException{
    public MemberDuplicateInProjectException(String message) {
        super(message, ErrorCode.MEMBER_DUPLICATE_IN_PROJECT);
    }
}
