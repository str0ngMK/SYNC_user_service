package user.service.kafka.task;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.MemberService;
import user.service.UserService;
import user.service.entity.User;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.task.event.TaskCreateEvent;
import user.service.web.dto.task.request.CreateTaskRequestDto;

@Service
@RequiredArgsConstructor
public class KafkaTaskProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserService userService;
    private final MemberService memberService;
    private final WebClient.Builder webClient;
    private static final String TOPIC = "task-create-topic";
    public ResponseMessage sendCreateTaskEvent(CreateTaskRequestDto createTaskRequestDto) {
        //http://129.213.161.199:31585/project/api/v1/find
        //http://localhost:8070/project/api/v1/find
        String baseUrl = "http://129.213.161.199:31585/project/api/v1/find";
        String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("projectId", createTaskRequestDto.getProjectId())
                .toUriString();
        ResponseMessage responseMessage = webClient.build()
                .post()
                .uri(urlWithQueryParam)
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .block();
        // 프로젝트 존재 여부 확인
        if (!responseMessage.isResult()) {
            return new ResponseMessage("프로젝트가 존재하지 않습니다.", false, createTaskRequestDto.getProjectId());
        }

        User user = userService.findUserEntity(userService.getCurrentUserId());
        // 프로젝트의 멤버인지 확인
        memberService.findMemberByUserIdAndProjectId(user.getId(), createTaskRequestDto.getProjectId());

        TaskCreateEvent event = new TaskCreateEvent(createTaskRequestDto);
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, event);
        record.headers().remove("spring.json.header.types");
        kafkaTemplate.send(record);
        return new ResponseMessage("업무 생성 이벤트 생성", true, createTaskRequestDto);
    }
}
