package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/api/alarm/url", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AlarmUrlController {
//	private final AlarmUrlService alarmUrlService;
//	private final UserService userService;
//
//	@Operation(summary = "알림 URL 불러오기", description = "사용자 고유 알림 URL을 가져옵니다.")
//	@GetMapping("/get")
//	public ResponseEntity<ResponseMessage> getAlarmUrl(){
//		String userId = userService.getCurrentUserId();
//		return ResponseEntity.ok().body(alarmUrlService.getAlarmUrl(userId));
//	}

}
