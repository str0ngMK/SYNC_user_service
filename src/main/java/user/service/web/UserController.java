package user.service.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import user.service.UserService;
import user.service.web.dto.request.ModifyPwdRequestDto;
import user.service.web.dto.request.ModifyUserInfoRequestDto;
import user.service.global.advice.ResponseMessage;

@RestController
@RequestMapping("/user/api/")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	//.requestMatchers("/user").hasAnyAuthority("USER") USER 계정 로그인 필요
	@GetMapping("info")
	public ResponseEntity<ResponseMessage> getCurrentUserInfo(){
		return ResponseEntity.ok().body(userService.getUserInfo());
	}
	@PutMapping("pwd")
	public ResponseEntity<ResponseMessage> modifyPwd(@Valid @RequestBody ModifyPwdRequestDto body){
		String userId = userService.getCurrentUserId();
		UserDetails userDetails = userService.loadUserByUsername(userId);
		return ResponseEntity.ok().body(userService.modifyPwd(body, userDetails));
	}
	@PutMapping("Info")
	public ResponseEntity<ResponseMessage> modifyUserInfo(@RequestBody @Valid ModifyUserInfoRequestDto body) {
		String userId = userService.getCurrentUserId();
		return ResponseEntity.ok().body(userService.modifyUserInfo(body, userId));
	}
//	@ResponseBody
//	@DeleteMapping("remove")
//	public ResponseEntity<ResponseMessage> removeUser() {
//		return ResponseEntity.ok().body(userService.remove(userService.getCurrentUserId()));
//	}
}
