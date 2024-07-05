package user.service.kafka.project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import user.service.UserService;
import user.service.kafka.project.event.CreateMemberAtProjectEvent;
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProjectConsumerService {
    private static final String TOPIC1 = "member-create-at-project-topic";
    private final UserService userService;
    @KafkaListener(topics = TOPIC1, groupId = "project_create_group")
    public void listenProjectCreateEvent(CreateMemberAtProjectEvent event) {
        try {
            // JSON 문자열을 CreateMemberAtProjectEvent 객체로 변환
            String userId = event.getUserId();
            String projectId = event.getProjectId();
            // 이벤트 처리
            userService.createMemberAtProject(userId, projectId);
            // 처리 로그 출력
            log.info("Processed CreateMemberAtProjectEvent for userId: " + userId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
