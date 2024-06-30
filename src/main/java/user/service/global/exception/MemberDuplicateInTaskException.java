package user.service.global.exception;


import user.service.global.advice.ErrorCode;

public class MemberDuplicateInTaskException extends BusinessException{
    public MemberDuplicateInTaskException(String message) {
        super(message, ErrorCode.MEMBER_DUPLICATE_IN_TASK);
    }
}
