package user.service.kafka.project;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user.service.web.dto.project.request.CreateProjectRequestDto;
import user.service.kafka.project.event.ProjectCreateEvent;

@Service
@RequiredArgsConstructor
public class KafkaProjectProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "project-create-topic";
    public void sendCreateProjectEvent(CreateProjectRequestDto projectCreateRequestDto, String userId) {
        ProjectCreateEvent event = new ProjectCreateEvent(projectCreateRequestDto, userId);
        kafkaTemplate.send(TOPIC, event);
    }

}