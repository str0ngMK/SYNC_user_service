package user.service.dto.alarm.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class SendAlarm {
	private UUID alarmId;
	private UUID url;
	private String message;
	private Timestamp createdAt;
}
