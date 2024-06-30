package user.service.global.advice;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;
import user.service.global.exception.AuthorizationFailureException;

@Component
public class AuthenticationEvents {
    @EventListener
    public void onFailure(AuthorizationDeniedEvent failure) {
        throw new AuthorizationFailureException("권한이 없는 경로입니다. 해당 권한을 갖고있는 계정으로 로그인하세요.", ErrorCode.USER_FAILED_AUTHORIZATION);
    }
}