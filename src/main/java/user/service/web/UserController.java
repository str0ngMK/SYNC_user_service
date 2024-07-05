package user.service.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import user.service.UserService;
import user.service.dto.request.ModifyPwdRequestDto;
import user.service.dto.request.ModifyUserInfoRequestDto;
import user.service.global.advice.ResponseMessage;
import user.service.jwt.dto.CustomUserDetails;
import user.service.oauth2.CustomOAuth2User;

@RestController
@RequestMapping("api/user/")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	//.requestMatchers("/user").hasAnyAuthority("USER") USER 계정 로그인 필요
	
	@GetMapping("info")
	public ResponseEntity<ResponseMessage> getUserInfo(){
		return ResponseEntity.ok().body(userService.getUserInfo());
	}
	
	@PutMapping("modify/pwd")
	public ResponseEntity<ResponseMessage> modifyPwd(@Valid @RequestBody ModifyPwdRequestDto body){
		String userId = userService.getCurrentUserId();
		UserDetails userDetails = userService.loadUserByUsername(userId);
		return ResponseEntity.ok().body(userService.modifyPwd(body, userDetails));
	}
	
	@PutMapping("modify/userInfo")
	public ResponseEntity<ResponseMessage> modifyUserInfo(@RequestBody ModifyUserInfoRequestDto body) {
		String userId = userService.getCurrentUserId();
		return ResponseEntity.ok().body(userService.modifyUserInfo(body, userId));
	}
	
	@ResponseBody
	@PostMapping("remove")
	public ResponseEntity<ResponseMessage> removeUser() {
		return ResponseEntity.ok().body(userService.remove(userService.getCurrentUserId()));
	}
}
