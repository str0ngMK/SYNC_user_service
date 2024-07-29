package user.service.web;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.global.advice.SuccessResponse;
import user.service.kafka.task.KafkaTaskProducerService;
import user.service.web.dto.project.request.UpdateProjectRequestDto;
import user.service.web.dto.task.request.*;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final KafkaTaskProducerService kafkaTaskProducerService;
    @Operation(summary = "업무를 생성하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : CreateTaskRequestDto")
    @PostMapping("/user/api/task")
    public SuccessResponse createTask(@RequestBody @Valid CreateTaskRequestDto createTaskRequestDto) {
        return kafkaTaskProducerService.sendCreateTaskEvent(createTaskRequestDto);
    }
    //해당 업무의 자식 업무만 조회합니다.
    @Operation(summary = "해당 업무의 자식 업무를 조회하기 위한 API", description = "HOST = 129.213.161.199:31585 <br>" +
            "ValidationDetails : GetTaskRequestDto")
    @GetMapping("api/task/OnlyChildrenTasks,")
    public void getOnlyChildrenTasks(@RequestBody @Valid GetTaskRequestDto getTaskRequestDto) {
    }
    //해당 업무를 삭제합니다.
    @Operation(summary = "업무를 삭제하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : DeleteTaskRequestDto")
    @DeleteMapping("/user/api/task")
    public SuccessResponse deleteTask(@RequestBody @Valid DeleteTaskRequestDto deleteTaskRequestDto) {
        return kafkaTaskProducerService.sendDeleteTaskEvent(deleteTaskRequestDto);
    }
    @Operation(summary = "업무를 수정하기 위한 API", description = "HOST = 150.136.153.235:30080")
    @PutMapping("/user/api/task")
    public SuccessResponse updateTask(@RequestBody @Valid UpdateTaskRequestDto updateTaskRequestDto) {
        //업무 업데이트 이벤트 생성 로직 추가
        return kafkaTaskProducerService.sendUpdateTaskEvent(updateTaskRequestDto);
    }

}
