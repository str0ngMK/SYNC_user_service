package user.service.kafka.project;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user.service.dto.project.request.CreateProjectRequestDto;

@Service
@RequiredArgsConstructor
public class KafkaProjectProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "project-create-topic";
    public void sendCreateProjectEvent(CreateProjectRequestDto projectCreateRequestDto, String userId) {
        ProjectCreateEvent event = new ProjectCreateEvent(projectCreateRequestDto, userId);
        kafkaTemplate.send(TOPIC, event);
    }
    @Getter
    public static class ProjectCreateEvent{
        private CreateProjectRequestDto projectCreateRequestDto;
        private String userId;
        public ProjectCreateEvent(CreateProjectRequestDto projectCreateRequestDto, String userId) {
            this.projectCreateRequestDto = projectCreateRequestDto;
            this.userId = userId;
        }
    }
}