package user.service.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import user.service.global.advice.ErrorCode;
import user.service.global.advice.ResponseMessage;
import user.service.global.exception.AuthenticationFailureException;
import user.service.jwt.dto.CustomUserDetails;
import user.service.jwt.util.JWTUtil;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final HandlerExceptionResolver exceptionResolver;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            //클라이언트 요청에서 id, password 추출
            String id = obtainUsername(request);
            String password = obtainPassword(request);
            //스프링 시큐리티에서 id와 password를 검증하기 위해서 token에 담는다.
            // (token이 AuthenticationManager로 넘겨질 때 dto 역할을 한다.)
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password, null);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        } catch (Exception e) {
            exceptionResolver.resolveException(request, response, null, e);
        }
        return null;
    }
    //로그인 성공시 실행하는 메소드 (이곳에서 JWT를 발급합니다.)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        //UserDetailsS
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String username = customUserDetails.getUsername();
        String name = customUserDetails.getName();
        String infoSet = customUserDetails.getInfoSet();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        String token = jwtUtil.createJwt(username, role, 60*30*1000L, infoSet, name);
        ResponseCookie jwtCookie = createCookie("JWT_TOKEN",  token);

        // JSON 객체 생성
        UserResponse userResponse = new UserResponse(username, name);

        // ObjectMapper를 사용하여 JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResponseMessage.builder().message("success").build());
        response.addHeader("Set-Cookie", jwtCookie.toString());
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        // JSON 스트링을 response body에 작성
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();
    }
    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        throw new AuthenticationFailureException("패스워드가 잘못되었습니다.", ErrorCode.USER_FAILED_AUTHENTICATION);
    }
    private ResponseCookie createCookie(String key, String value) {
        ResponseCookie cookie = ResponseCookie.from(key, value)
                .path("/")
                .sameSite("None")
                .httpOnly(false)
                .secure(true)
                .maxAge(30*60)
                .build();
        return cookie;
    }
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("id");
    }
    static class UserResponse {
        private String userId;
        private String name;

        public UserResponse(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        // Getters (필요한 경우 Setters도 추가할 수 있습니다)
        public String getUsername() {
            return userId;
        }

        public String getName() {
            return name;
        }
    }
}
