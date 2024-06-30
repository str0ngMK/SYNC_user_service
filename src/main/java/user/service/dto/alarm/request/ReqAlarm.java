package user.service.dto.alarm.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReqAlarm {
	private UUID alarmId;
	private long userId;
	private String message;
}
