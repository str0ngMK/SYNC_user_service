package user.service.web;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;
import user.service.UserService;
import user.service.web.dto.request.SignupRequestDto;

import java.net.URI;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {
	private final UserService userService;
	private WebClient webClient;

	private final Environment env;
	@PostConstruct
	public void init() {
		this.webClient = WebClient.builder()
				.baseUrl("https://nid.naver.com/oauth2.0") // Base URL of the OAuth provider
				.build();
	}

	@PostMapping("/user/oauth2/authorization/naver")
	public ResponseEntity<String> redirectToNaverOAuth(HttpServletRequest request) {
		// Construct base URL from HttpServletRequest
		String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

		// Use the constructed base URL in the redirect_uri parameter
		String redirectUri = this.webClient.post()
				.uri("/oauth2/authorization/naver")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.body(BodyInserters.fromFormData("client_id", env.getProperty("spring.security.oauth2.client.registration.naver.client-id"))
						.with("response_type", "code")
						.with("redirect_uri", baseUrl + "/login/oauth2/code/naver") // Dynamically set redirect_uri
						.with("scope", "request_scopes"))
				.retrieve()
				.bodyToMono(String.class)
				.block(); // Blocking for simplicity; consider using async patterns

		// Assuming the OAuth provider responds with a redirect URI
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUri)).build();
	}
	@ResponseBody
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
		userService.signup(signupRequestDto);
		return ResponseEntity.ok("OK");
	}

}