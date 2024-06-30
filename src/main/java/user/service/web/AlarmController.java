package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/user/alarm", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AlarmController {
//	private final AlarmService alarmService;
//	private final UserService userService;
//
//	@Operation(summary = "과거 알림 불러오기", description = "데이터베이스에 저장 된 알림 정보를 불러옵니다.")
//	@GetMapping("/list")
//	public ResponseEntity<ResponseMessage> getAlarmList() {
//		String userId = userService.getCurrentUserId();
//		return ResponseEntity.ok().body(alarmService.getAlarmList(userId));
//	}
//
//	@Operation(summary = "과거 알림 닫기", description = "데이터베이스에 저장 된 알림을 삭제 합니다.")
//	@DeleteMapping("/close")
//	public ResponseEntity<ResponseMessage> deleteAlarm(@Parameter(description = "알림 PK 값") @RequestParam(name="alarmId") UUID alarmId){
//		return ResponseEntity.ok().body(alarmService.deleteAlarm(alarmId));
//	}


}
