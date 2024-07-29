package user.service.kafka.member;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user.service.MemberService;
import user.service.UserService;
import user.service.entity.User;
import user.service.global.advice.SuccessResponse;
import user.service.kafka.member.event.IsExistProjectByMemberAddToProjectEvent;
import user.service.kafka.task.event.TaskCreateEvent;
import user.service.kafka.task.event.TaskDeleteEvent;
import user.service.kafka.task.event.TaskUpdateEvent;
import user.service.kafka.task.event.UserAddToTaskEvent;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;
import user.service.web.dto.task.request.CreateTaskRequestDto;
import user.service.web.dto.task.request.DeleteTaskRequestDto;
import user.service.web.dto.task.request.UpdateTaskRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaMemberProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "is-exist-project-by-member-add-to-project-topic";
    /**
     * 프로젝트 존재 여부 확인 이벤트 생성
     * @param projectId, userIds
     * @return
     */
    public SuccessResponse isExistProjectByMemberAddToProject(Long projectId, List<String> userIds) {
        IsExistProjectByMemberAddToProjectEvent event = new IsExistProjectByMemberAddToProjectEvent(projectId, userIds);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new SuccessResponse("업무 생성 이벤트 생성", true, event);
    }
}
