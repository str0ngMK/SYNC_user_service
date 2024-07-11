package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.task.KafkaTaskProducerService;
import user.service.web.dto.task.request.CreateTaskRequestDto;
import user.service.web.dto.task.request.DeleteTaskRequestDto;
import user.service.web.dto.task.request.GetTaskRequestDto;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final KafkaTaskProducerService kafkaTaskProducerService;
    private final WebClient.Builder webClient;
    @PostMapping("/user/api/task/create")
    public ResponseMessage createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
        return kafkaTaskProducerService.sendCreateTaskEvent(createTaskRequestDto);
    }
    //해당 업무의 자식 업무만 조회합니다.
    @GetMapping("api/task/getOnlyChildrenTasks")
    public ResponseMessage getOnlyChildrenTasks(@RequestBody GetTaskRequestDto getTaskRequestDto) {
        String baseUrl = "http://129.213.161.199:31585/project/tasks/api/v1/getChildren";
//        String baseUrl = "http://localhost:8070/tasks/api/v1/getChildren";
        String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("taskId", getTaskRequestDto.getTaskId())
                .toUriString();
        ResponseMessage responseMessage = webClient.build()
                .get()
                .uri(urlWithQueryParam)
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .block();
        if (!responseMessage.isResult()) {
            return new ResponseMessage("해당 업무는 존재하지 않습니다.", false, getTaskRequestDto.getTaskId());
        }
        return new ResponseMessage("업무 생성 이벤트 생성", true, responseMessage.getValue());
    }
    //해당 업무를 삭제합니다.
    @PostMapping("/user/api/task/delete")
    public ResponseMessage deleteTask(@RequestBody DeleteTaskRequestDto deleteTaskRequestDto) {
        return kafkaTaskProducerService.sendDeleteTaskEvent(deleteTaskRequestDto);
    }
}
