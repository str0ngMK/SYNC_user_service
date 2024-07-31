package user.service.kafka.task;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import user.service.MemberService;
import user.service.UserService;
import user.service.entity.User;
import user.service.global.advice.SuccessResponse;
import user.service.kafka.task.event.TaskCreateEvent;
import user.service.kafka.task.event.TaskDeleteEvent;
import user.service.kafka.task.event.TaskUpdateEvent;
import user.service.kafka.task.event.UserAddToTaskEvent;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;
import user.service.web.dto.task.request.CreateTaskRequestDto;
import user.service.web.dto.task.request.DeleteTaskRequestDto;
import user.service.web.dto.task.request.UpdateTaskRequestDto;

@Service
@RequiredArgsConstructor
public class KafkaTaskProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserService userService;
    private final MemberService memberService;
    private static final String TOPIC = "task-create-topic";
    private static final String TOPIC1 = "task-add-user-topic";
    private static final String TOPIC2 = "task-delete-topic";
    private static final String TOPIC3 = "task-update-topic";
    /**
     * 업무 생성 이벤트 생성
     * @param createTaskRequestDto
     * @return
     */
    public SuccessResponse sendCreateTaskEvent(CreateTaskRequestDto createTaskRequestDto) {
        User user = userService.findUserEntity(userService.getCurrentUserId());
        // 프로젝트의 멤버인지 확인
        memberService.findMemberByUserIdAndProjectId(user.getId(), createTaskRequestDto.getProjectId());
        TaskCreateEvent event = new TaskCreateEvent(createTaskRequestDto);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new SuccessResponse("업무 생성 이벤트 생성", true, createTaskRequestDto);
    }
    /**
     * 업무 담당자 배정 이벤트 생성
     * @param memberMappingToTaskRequestDto
     * @return
     */
    public SuccessResponse sendAddUserToTaskEvent(MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
        SuccessResponse responseMessage = memberService.allMembersInSameProject(memberMappingToTaskRequestDto);
        if(responseMessage.isResult()){
            @SuppressWarnings("unchecked")
            List<Long> userIds = (List<Long>) responseMessage.getValue();
            UserAddToTaskEvent event = new UserAddToTaskEvent(userIds, memberMappingToTaskRequestDto.getTaskId());
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC1, event);
            record.headers().remove("spring.json.header.types");
            kafkaTemplate.send(record);
            return new SuccessResponse("업무 담당자 배정 이벤트 생성", true, memberMappingToTaskRequestDto);
        }else{
            return new SuccessResponse(responseMessage.getMessage(), false, responseMessage.getValue());
        }
    }
    public SuccessResponse sendDeleteTaskEvent(DeleteTaskRequestDto deleteTaskRequestDto) {
        User user = userService.findUserEntity(userService.getCurrentUserId());
        // 프로젝트의 멤버인지 확인
        memberService.findMemberByUserIdAndProjectId(user.getId(), deleteTaskRequestDto.getProjectId());
        TaskDeleteEvent event = new TaskDeleteEvent(deleteTaskRequestDto.getTaskId());
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC2, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new SuccessResponse("업무 삭제 이벤트 생성", true, deleteTaskRequestDto);
    }
    public SuccessResponse sendUpdateTaskEvent(UpdateTaskRequestDto updateTaskRequestDto) {
        User user = userService.findUserEntity(userService.getCurrentUserId());
        memberService.findMemberByUserIdAndProjectId(user.getId(), updateTaskRequestDto.getProjectId());
        TaskUpdateEvent event = new TaskUpdateEvent(updateTaskRequestDto);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC3, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new SuccessResponse("업무 수정 이벤트 생성", true, updateTaskRequestDto);
    }
}
