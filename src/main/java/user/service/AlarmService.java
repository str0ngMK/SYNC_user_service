package user.service;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import user.service.entity.User;
import user.service.repository.UserRepository;
import user.service.web.dto.alarm.request.AlarmListRequestDto;

@Service
@RequiredArgsConstructor
public class AlarmService {
//	// kafka
//	private final KafkaTemplate<String, Object> kafkaTemplate;
//	private final KafkaAdmin kafkaAdmin;
//	private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory;
//
//	// repository
//	private final UserRepository userRepository;
//
//	// common
//	private final ObjectMapper objectMapper;
//	private final SimpMessagingTemplate messagingTemplate;
//
//	public void reqAlarmList(String loginId) {
//		AlarmListRequestDto dto = new AlarmListRequestDto();
//		User user = userRepository.findByAuthenticationUserId(loginId);
//		dto.setUserId(user.getId());
//
//		String mapper = null;
//		try {
//			mapper = objectMapper.writeValueAsString(dto);
//		} catch (Exception e) {
//			// Object mapper로 변환 불가능한 경우 처리
//		}
//		kafkaTemplate.send("reqAlarmList", mapper);
//	}
//
//	@KafkaListener(topics = "resAlarmList")
//	public void listen(String message, Acknowledgment acknowledgment) {
//		System.out.println("★ Kafka Listener 실행! ★ : " + message);
//
//		Map<String, Object> map = null;
//		try {
//			map = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		messagingTemplate.convertAndSend("/response/" + map.get("userId"), message);
//
//		acknowledgment.acknowledge();
//	}


//	public SseEmitter resAlarmList(String loginId) {
//		User userId = userRepository.findByAuthenticationUserId(loginId);
//
//		SseEmitter emitter = new SseEmitter();
//		emitters.put(loginId, emitter);
//		
////		if(emitters.get("test") == null) {
////			
////		}
//
//		emitter.onTimeout(() -> {
//			System.out.println("★ 타임 아웃! ★");
//			try {
////				emitters.remove(emitter);
//				emitters.remove(loginId);
//				emitter.complete();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		emitter.onCompletion(() -> {
//			System.out.println("★ 완료! ★");
//			try {
////				emitters.remove(emitter);
//				emitters.remove(loginId);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		emitter.onError((Throwable ex) -> {
//			System.out.println("★ 에러! ★");
//			try {
////				emitters.remove(emitter);
//				emitters.remove(loginId);
//				emitter.complete();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		return emitter;
//	}

//	public Flux<String> getAlarmList() {
//		return Flux.create(sink -> {
//			try {
//				String message = queue.take();
//				sink.next(message);
//			} catch (InterruptedException e) {
//				sink.error(e);
//				Thread.currentThread().interrupt();
//			}
//		});
//	}
//
//	@KafkaListener(topics = "resAlarmList")
//	public void listen(ConsumerRecord<?, ?> record) {
//		String message = record.value().toString();
//		queue.offer(message);
//	}
}
