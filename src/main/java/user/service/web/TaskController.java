package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.task.KafkaTaskProducerService;
import user.service.web.dto.project.request.UpdateProjectRequestDto;
import user.service.web.dto.task.request.*;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final KafkaTaskProducerService kafkaTaskProducerService;
    private final WebClient.Builder webClient;
    @PostMapping("/user/api/task")
    public ResponseMessage createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
        return kafkaTaskProducerService.sendCreateTaskEvent(createTaskRequestDto);
    }
    //해당 업무의 자식 업무만 조회합니다.
    @GetMapping("api/task/OnlyChildrenTasks")
    public ResponseMessage getOnlyChildrenTasks(@RequestBody GetTaskRequestDto getTaskRequestDto) {
        String baseUrl = "https://129.213.161.199:32308/project/tasks/api/v1/getChildren";
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
        return new ResponseMessage("업무 조회 완료", true, responseMessage.getValue());
    }
    //해당 업무를 삭제합니다.
    @DeleteMapping("/user/api/task")
    public ResponseMessage deleteTask(@RequestBody DeleteTaskRequestDto deleteTaskRequestDto) {
        return kafkaTaskProducerService.sendDeleteTaskEvent(deleteTaskRequestDto);
    }
    @PutMapping("/user/api/task")
    public ResponseMessage updateTask(@RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        //업무 업데이트 이벤트 생성 로직 추가
        return kafkaTaskProducerService.sendUpdateTaskEvent(updateTaskRequestDto);
    }
    @GetMapping("task/user")
    public void getMemberFromTask(@RequestBody GetMemberFromTaskRequestDto getMemberFromTaskRequestDto) {
    }
}
