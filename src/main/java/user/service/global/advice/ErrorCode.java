package user.service.global.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	
    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    BLANK_INPUT_VALUE(400,"C007","Blank Input Value"),
    PROJECT_VALIDATE(401,"C008","프로젝트에 같은 멤버가 존재합니다."),

    // User 예시
    USERID_DUPLICATE(409, "U001", "UserId is duplicated"),
    SOCIAL_EMAIL_EXIST(410, "U002", "Email has already been used in social register"),
    USER_FAILED_AUTHORIZATION(411, "U003", "This user is not authorized."),
    USER_FAILED_AUTHENTICATION(412, "U004", "Invalid password for this user."),
    USER_NOT_FOUND(400, "U005", "해당 유저는 존재하지 않습니다."),
    USER_ID_DUPLICATED(400, "U006", "같은 아이디가 존재합니다."),

    // Member 예시
    MEMBER_DUPLICATE_IN_PROJECT(409,"M001","이미 프로젝트에 존재하는 멤버입니다."),
    MEMBER_DUPLICATE_IN_TASK(409,"M002","이미 업무에 존재하는 멤버입니다."),
	
	// Global
	UNKNOWN_ERROR(500, "G001", "시스템에 오류가 발생 하였습니다. 관리자에게 문의 해주세요."),
	IDENTICAL_VALUE(200, "G002", "동일한 값은 변경할 수 없습니다.");


    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}