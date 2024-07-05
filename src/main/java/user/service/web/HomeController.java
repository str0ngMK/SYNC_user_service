package user.service.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import user.service.UserService;
import user.service.dto.request.SignupRequestDto;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;
	@ResponseBody
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
		userService.signup(signupRequestDto);
		return ResponseEntity.ok("OK");
	}
	@GetMapping("/user/oauth2/authorization/naver")
	public RedirectView redirectToNaverOAuth(HttpServletRequest request) {
		// 현재 요청의 스키마, 호스트, 포트를 가져옵니다.
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/user";
		// OAuth 로그인 경로를 포함한 전체 URL을 생성합니다.
		String redirectUrl = baseUrl + "/oauth2/authorization/naver";
		// 생성된 URL로 리다이렉트합니다.
		return new RedirectView(redirectUrl);
	}
}