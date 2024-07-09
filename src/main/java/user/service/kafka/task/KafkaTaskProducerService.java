package user.service.kafka.task;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import user.service.MemberService;
import user.service.UserService;
import user.service.entity.User;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.task.event.TaskCreateEvent;
import user.service.kafka.task.event.UserAddToTaskEvent;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;
import user.service.web.dto.task.request.CreateTaskRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaTaskProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserService userService;
    private final MemberService memberService;
    private static final String TOPIC = "task-create-topic";
    private static final String TOPIC1 = "task-add-user-topic";
    public ResponseMessage sendCreateTaskEvent(CreateTaskRequestDto createTaskRequestDto) {

        User user = userService.findUserEntity(userService.getCurrentUserId());
        // 프로젝트의 멤버인지 확인
        memberService.findMemberByUserIdAndProjectId(user.getId(), createTaskRequestDto.getProjectId());

        TaskCreateEvent event = new TaskCreateEvent(createTaskRequestDto);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new ResponseMessage("업무 생성 이벤트 생성", true, createTaskRequestDto);
    }
    public ResponseMessage sendAddUserToTaskEvent(MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
        ResponseMessage responseMessage = memberService.allMembersInSameProject(memberMappingToTaskRequestDto);
        if(responseMessage.isResult()){
            @SuppressWarnings("unchecked")
            List<Long> userIds = (List<Long>) responseMessage.getValue();
            UserAddToTaskEvent event = new UserAddToTaskEvent(userIds, memberMappingToTaskRequestDto.getTaskId());
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC1, event);
            record.headers().remove("spring.json.header.types");
            kafkaTemplate.send(record);
            return new ResponseMessage("업무 담당자 배정 이벤트 생성", true, memberMappingToTaskRequestDto);
        }else{
            return new ResponseMessage(responseMessage.getMessage(), false, responseMessage.getValue());
        }

    }

}
