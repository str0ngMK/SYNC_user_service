package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.service.global.advice.ResponseMessage;

@RestController
@RequestMapping("user/api/task")
@RequiredArgsConstructor
public class TaskController {
//
//    @PostMapping("/create")
//    public ResponseEntity<ResponseMessage> createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
//        return ResponseEntity.ok().body(taskService.createTask(createTaskRequestDto));
//    }
//
//    //해당 업무의 모든 하위 업무를 조회합니다.
//    @GetMapping("/getSubTasks")
//    public ResponseEntity<ResponseMessage> getSubTasks(@RequestBody GetTaskRequestDto getTaskRequestDto) {
//    	return ResponseEntity.ok().body(taskService.getAllSubTask(getTaskRequestDto.getTaskId()));
//    }
//
//    //해당 업무의 자식 업무만 조회합니다.f
//    @GetMapping("/getOnlyChildrenTasks")
//    public ResponseEntity<ResponseMessage> getOnlyChildrenTasks(@RequestBody GetTaskRequestDto getTaskRequestDto) {
//    	return ResponseEntity.ok().body(taskService.getOnlyChildrenTasks(getTaskRequestDto.getTaskId()));
//    }
//    //해당 업무를 삭제합니다.
//    @PostMapping("/delete")
//    public ResponseEntity<ResponseMessage> deleteTask(@RequestBody DeleteTaskRequestDto deleteTaskRequestDto) {
//        return ResponseEntity.ok().body(taskService.deleteTask(deleteTaskRequestDto));
//    }
}
