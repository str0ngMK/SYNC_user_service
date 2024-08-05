package user.service.kafka.project;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import user.service.MemberService;
import user.service.UserService;
import user.service.kafka.project.event.ProjectDeleteEvent;
import user.service.kafka.project.event.ProjectUpdateEvent;
import user.service.web.dto.project.request.CreateProjectRequestDto;
import user.service.kafka.project.event.ProjectCreateEvent;
import user.service.web.dto.project.request.DeleteProjectRequestDto;
import user.service.web.dto.project.request.UpdateProjectRequestDto;

@Service
@RequiredArgsConstructor
public class KafkaProjectProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MemberService memberService;
    private final UserService userService;

    private static final String TOPIC = "project-create-topic";
    private static final String TOPIC1 = "project-delete-topic";
    private static final String TOPIC2 = "project-update-topic";
    public void sendCreateProjectEvent(CreateProjectRequestDto projectCreateRequestDto, String userId) {
        ProjectCreateEvent event = new ProjectCreateEvent(projectCreateRequestDto, userId);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
    }
    public void sendDeleteProjectEvent(DeleteProjectRequestDto projectDeleteRequestDto, String userId) {
        ProjectDeleteEvent event = new ProjectDeleteEvent(projectDeleteRequestDto.getProjectId(), userId);
        //프로젝트 생성자인지 확인
        memberService.isManager(
            //프로젝트 멤버인지 확인
            memberService.findMemberByUserIdAndProjectId(
                userService.getUserEntityId(userId), projectDeleteRequestDto.getProjectId()
            )
            .getId()
        );
        //해당 프로젝트에 속한 멤버를 모두 삭제
        memberService.deleteMembersByProjectId(projectDeleteRequestDto.getProjectId());
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC1, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
    }
    public void updateProject(UpdateProjectRequestDto updateProjectRequestDto) {
        ProjectUpdateEvent event = new ProjectUpdateEvent(updateProjectRequestDto);
        //프로젝트 관리자인지 확인
        memberService.isManager(
            //프로젝트 멤버인지 확인
            memberService.findMemberByUserIdAndProjectId(
                userService.getUserEntityId(userService.getCurrentUserId()), updateProjectRequestDto.getProjectId()
            )
            .getId()
        );
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC2, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
    }
}