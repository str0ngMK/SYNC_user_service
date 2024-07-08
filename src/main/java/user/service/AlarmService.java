package user.service;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import user.service.dto.alarm.request.AlarmListRequestDto;
import user.service.entity.User;
import user.service.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AlarmService {
	// kafka
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final KafkaAdmin kafkaAdmin;
	private final KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory;

	// repository
	private final UserRepository userRepository;

	// common
	private final ObjectMapper objectMapper;
//	private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
	private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	private final SimpMessagingTemplate messagingTemplate;

	public void reqAlarmList(String loginId) {
		AlarmListRequestDto dto = new AlarmListRequestDto();
		User user = userRepository.findByAuthenticationUserId(loginId);
		dto.setUserId(user.getId());

		String mapper = null;
		try {
			mapper = objectMapper.writeValueAsString(dto);
		} catch (Exception e) {
			// Object mapper로 변환 불가능한 경우 처리
		}
		kafkaTemplate.send("reqAlarmList", mapper);
	}
	
	
	@KafkaListener(topics = "resAlarmList")
	public void listen(String message, Acknowledgment acknowledgment) {
		System.out.println("★ Kafka Listener 실행! ★ : " + message);
		sendMessage(message);
		acknowledgment.acknowledge();
	}
	
	private void sendMessage(String message) {
//		emitters.forEach(emitter -> {
//			try {
//				emitter.send(message, MediaType.TEXT_PLAIN);
//			} catch (Exception e) {
//				emitter.complete();
//				emitters.remove(emitter);
//				e.printStackTrace();
//			}
//		});
		messagingTemplate.convertAndSend("/alarm/response/list", ServerSentEvent.builder()
                .event("message")
                .data(message)
                .build());
	}
	
	public SseEmitter resAlarmList(String loginId) {
		User userId = userRepository.findByAuthenticationUserId(loginId);
		
		SseEmitter emitter = new SseEmitter();
		emitters.add(emitter);
		
		emitter.onTimeout(() -> {
			System.out.println("★ 타임 아웃! ★");
            try {
                emitters.remove(emitter);
                emitter.complete();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });

        emitter.onCompletion(() -> {
        	System.out.println("★ 완료! ★");
            try {
                emitters.remove(emitter);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });

        emitter.onError((Throwable ex) -> {
        	System.out.println("★ 에러! ★");
            try {
                emitters.remove(emitter);
                emitter.complete();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        });

		return emitter;
	}
	

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
