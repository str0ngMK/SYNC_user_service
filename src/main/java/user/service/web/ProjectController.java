package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.service.UserService;
import user.service.web.dto.project.request.CreateProjectRequestDto;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.project.KafkaProjectProducerService;

@RestController
@RequestMapping("user/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final UserService userService;
    private final KafkaProjectProducerService kafkaProducerService;
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createProject(@RequestBody CreateProjectRequestDto projectCreateRequestDto) {
        String userId = userService.getCurrentUserId();
        kafkaProducerService.sendCreateProjectEvent(projectCreateRequestDto, userId);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 생성 이벤트 생성").build());
    }
//
//    @PostMapping("/delete")
//    public ResponseEntity<ResponseMessage> deleteProject(@RequestBody DeleteProjectRequestDto projectDeleteRequestDto) {
//        return ResponseEntity.ok().body(projectService.deleteProject(projectDeleteRequestDto));
//    }
    
    /*
     * old
     */
//    @PostMapping("/get")
//    public ResponseEntity<ResponseMessage> getProjects(@RequestBody GetProjectsRequestDto getProjectsRequestDto) {
//        return ResponseEntity.ok().body(projectService.getProjects(getProjectsRequestDto));
//    }
    
    /*
     * new
     */
//    @GetMapping("/get")
//    public ResponseEntity<ResponseMessage> getProjects() {
//    	String userId = userService.getCurrentUserId();
//        return ResponseEntity.ok().body(projectService.getProjects(userId));
//    }
}
