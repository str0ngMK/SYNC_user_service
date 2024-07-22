package user.service.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import user.service.global.advice.ResponseMessage;
import user.service.jwt.util.JWTUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
@Component
@RequiredArgsConstructor
@Slf4j
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        String name = customUserDetails.getName();
        String infoSet = customUserDetails.getInfoSet();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();


        String token = jwtUtil.createJwt(username, role, 60*30*1000L, infoSet, name);

        response.setHeader("Authorization", "Bearer " + token);


        // ObjectMapper를 사용하여 JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResponseMessage.builder().message("success").build());
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        // JSON 스트링을 response body에 작성
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }

    //쿠키로 JWT 발급
    private ResponseCookie createCookie(String key, String value) {
        ResponseCookie cookie = ResponseCookie.from(key, value)
            .path("/")
            .sameSite("Lax")
            .httpOnly(false)
            .secure(false)
            .maxAge(30 * 60)
            .build();
        return cookie;
    }
}