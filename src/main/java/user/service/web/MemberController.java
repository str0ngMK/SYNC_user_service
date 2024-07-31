package user.service.web;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import user.service.MemberService;
import user.service.global.advice.SuccessResponse;
import user.service.kafka.task.KafkaTaskProducerService;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;
import user.service.web.dto.project.response.GetUserIdsByProjectsResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KafkaTaskProducerService kafkaTaskProducerService;
    @Operation(summary = "프로젝트에 멤버를 추가하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : MemberMappingToProjectRequestDto")
    @PostMapping("user/api/member/project")
    public SuccessResponse memberAddToProject(@RequestBody @Valid MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
        return memberService.memberAddToProject(memberMappingToProjectRequestDto);
    }
    @Operation(summary = "업무에 담당자를 추가하기 위한 API", description = "HOST = 150.136.153.235:30080 <br>" +
            "ValidationDetails : MemberMappingToTaskRequestDto")
    @PostMapping("user/api/member/task")
    public SuccessResponse memberAddToTask(@RequestBody @Valid MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
        return kafkaTaskProducerService.sendAddUserToTaskEvent(memberMappingToTaskRequestDto);
    }
    @Operation(summary = "업무의 담당자들을 가져오기 위한 API", description = "HOST = 129.213.161.199:31585")
    @GetMapping("/project/task/api/v1/users")
    public void getUsersFromTask(@RequestParam Long taskId) {
    }
    @Operation(summary = "프로젝트의 멤버들을 가져오기 위한 API", description = "HOST = 150.136.153.235:30080")
    @GetMapping("/api/v1/users")
    public List<GetUserIdsByProjectsResponseDto> getUsersFromProject(@RequestParam List<Long> projectIds) {
        return memberService.getUsersFromProjects(projectIds);
    }
}
