package user.service.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.service.MemberService;
import user.service.UserService;
import user.service.entity.User;
import user.service.web.dto.project.request.*;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.project.KafkaProjectProducerService;
import java.util.List;
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ProjectController {
    private final UserService userService;
    private final KafkaProjectProducerService kafkaProducerService;
    private final MemberService memberService;
    @Operation(summary = "프로젝트를 생성하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : CreateProjectRequestDto")
    @PostMapping("/user/api/project")
    public ResponseEntity<ResponseMessage> createProject(@RequestBody @Valid CreateProjectRequestDto projectCreateRequestDto) {
        String userId = userService.getCurrentUserId();
        kafkaProducerService.sendCreateProjectEvent(projectCreateRequestDto, userId);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 생성 이벤트 생성").build());
    }
    @Operation(summary = "프로젝트를 삭제하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : DeleteProjectRequestDto")
    @DeleteMapping("user/api/project")
    public ResponseEntity<ResponseMessage> deleteProject(@RequestBody @Valid DeleteProjectRequestDto projectDeleteRequestDto) {
        String userId = userService.getCurrentUserId();
        kafkaProducerService.sendDeleteProjectEvent(projectDeleteRequestDto, userId);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 삭제 이벤트 생성").build());
    }
    @Operation(summary = "프로젝트를 수정하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : UpdateProjectRequestDto")
    @PutMapping("/user/api/project")
    public ResponseEntity<ResponseMessage> updateProject(@RequestBody @Valid UpdateProjectRequestDto updateProjectRequestDto) {
        kafkaProducerService.updateProject(updateProjectRequestDto);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 업데이트 이벤트 생성").build());
    }
    @Operation(summary = "프로젝트들의 정보를 가져오기 위한 API", description = "HOST = 129.213.161.199:31585")
    @GetMapping("/project/api/v1")
    public void getProjects(@Parameter(description = "존재하지 않는 프로젝트 아이디 입력시 오류 발생") @RequestParam List<Long> projectIds) {
    }
    @Operation(summary = "유저가 속해있는 프로젝트들의 ID를 가져오기 위한 API", description = "HOST = 150.136.153.235:30080")
    @GetMapping("/project/api/v2")
    public ResponseMessage getProjectsByUserLoginId(@Parameter(description = "존재하지 않는 로그인 아이디 입력시 오류 발생") @RequestParam String userId) {
        Long userEntityId = userService.getUserEntityId(userId);
        List<Long> projectIds = memberService.getProjectIdsByUserId(userEntityId);
        return new ResponseMessage("해당 유저의 프로젝트 아이디 조회 완료", true, projectIds);
    }
}
