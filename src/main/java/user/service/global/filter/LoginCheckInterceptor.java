package user.service.global.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import user.service.jwt.dto.CustomUserDetails;
import user.service.oauth2.CustomOAuth2User;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isInfoSetDefault(authentication);
        return true;
    }

    private void isInfoSetDefault(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String userId = null;
            String name = null;
            String infoset = null;
            String role = null;
            if (authentication instanceof OAuth2AuthenticationToken) {
                CustomOAuth2User oauthToken = (CustomOAuth2User) authentication.getPrincipal();
                userId = oauthToken.getUsername(); // OAuth2로 인증된 경우 사용자 ID 추출
                name = oauthToken.getName();
                infoset = oauthToken.getInfoSet().toString();
                role = oauthToken.getRole().toString();
            } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
                userId = customUserDetails.getUsername();
                name = customUserDetails.getName();
                infoset = customUserDetails.getInfoSet().toString();
                role = customUserDetails.getRole().toString();
            }

            if (userId != null) {
                log.info("현재 유저 ID: " + userId);
                log.info("현재 유저 이름: " + name);
                log.info("현재 유저 권한: " + role);
                log.info("현재 유저 가입 경로: " + infoset);
            } else {
                log.info("Guest status.");
            }
        }
    }
}