package user.service.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import user.service.MemberService;
import user.service.UserService;
import user.service.web.dto.project.request.*;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.project.KafkaProjectProducerService;
import user.service.web.dto.project.response.GetProjectsFromProjectServiceResponseDto;
import user.service.web.dto.project.response.GetProjectsResponseDto;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ProjectController {
    private final UserService userService;
    private final MemberService memberService;
    private final WebClient.Builder webClient;
    private final KafkaProjectProducerService kafkaProducerService;
    @PostMapping("/user/api/project")
    public ResponseEntity<ResponseMessage> createProject(@RequestBody @Valid CreateProjectRequestDto projectCreateRequestDto) {
        String userId = userService.getCurrentUserId();
        kafkaProducerService.sendCreateProjectEvent(projectCreateRequestDto, userId);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 생성 이벤트 생성").build());
    }

    @DeleteMapping("user/api/project")
    public ResponseEntity<ResponseMessage> deleteProject(@RequestBody @Valid DeleteProjectRequestDto projectDeleteRequestDto) {
        String userId = userService.getCurrentUserId();
        kafkaProducerService.sendDeleteProjectEvent(projectDeleteRequestDto, userId);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 삭제 이벤트 생성").build());
    }
    @PutMapping("/user/api/project")
    public ResponseEntity<ResponseMessage> updateProject(@RequestBody @Valid UpdateProjectRequestDto updateProjectRequestDto) {
        kafkaProducerService.updateProject(updateProjectRequestDto);
        return ResponseEntity.ok().body(ResponseMessage.builder().message("프로젝트 업데이트 이벤트 생성").build());
    }
    @GetMapping("/api/project")
    public ResponseMessage getProjects(@RequestParam String userId) {
        List<Long> projectIds = memberService.getProjectIdsByUserId(userService.findUserEntity(userId).getId());
        GetProjectsRequestToProjectServiceDto requestDto = new GetProjectsRequestToProjectServiceDto(projectIds);
        String baseUrl = "http://129.213.161.199:31585/project/api/v1/get";
        List<GetProjectsFromProjectServiceResponseDto> getProjectsResponseDto = webClient.build()
                .post()
                .uri(baseUrl)
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<GetProjectsFromProjectServiceResponseDto>>() {})
                .block();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<GetProjectsResponseDto> responseDtos = getProjectsResponseDto.stream().map(project -> {
            String formattedStartDate = sdf.format(project.getStartDate());
            String formattedEndDate = sdf.format(project.getEndDate());
            List<Long> memberIds = memberService.getMemberIdsByProjectId(project.getProjectId());
            return new GetProjectsResponseDto(project.getProjectId(), project.getTitle(), project.getDescription(), formattedStartDate, formattedEndDate, memberIds);
        }).collect(Collectors.toList());
        return new ResponseMessage("프로젝트 조회 완료", true, responseDtos);
    }
}
