package user.service.kafka.alarm;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import user.service.web.AlarmController;

@Component
@RequiredArgsConstructor
public class KafkaAlarmConsumerService {
	
	private final AlarmController alarmController;
	
	@KafkaListener(topics = "resAlarmList")
	public void listen(String message) {
		alarmController.sendMessage(message);
	}

}
