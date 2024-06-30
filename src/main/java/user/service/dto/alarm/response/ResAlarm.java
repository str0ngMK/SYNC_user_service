package user.service.dto.alarm.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ResAlarm {	
	private UUID alarmId;
	private String message;
	private Timestamp createdAt;
}
