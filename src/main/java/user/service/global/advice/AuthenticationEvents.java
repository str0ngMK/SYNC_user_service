package user.service.global.advice;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import user.service.global.exception.AuthorizationFailureException;

@Component
public class AuthenticationEvents {
//    @EventListener
//    public void onFailure(AuthorizationDeniedEvent event) {
//    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    	if (request != null) {
//            String requestUrl = request.getRequestURL().toString();
//            throw new AuthorizationFailureException(requestUrl, ErrorCode.USER_FAILED_AUTHORIZATION);
//        } else {
//            throw new AuthorizationFailureException("No request context available", ErrorCode.USER_FAILED_AUTHORIZATION);
//        }
//    }
    @EventListener
    public void onFailure(AuthorizationDeniedEvent failure) {
        String errorMessage = "Authorization denied for the requested operation. pls login and try again.";
        throw new AuthorizationFailureException(errorMessage, ErrorCode.USER_FAILED_AUTHORIZATION);
    }
}