package user.service.kafka.project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import user.service.MemberService;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.kafka.project.event.CreateMemberAtProjectEvent;
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProjectConsumerService {
    private static final String TOPIC1 = "member-create-at-project-topic";
    private final MemberService memberService;
    @KafkaListener(topics = TOPIC1, groupId = "project_create_group")
    public void listenCreateMemberAtProjectEvent(CreateMemberAtProjectEvent event) {
        try {
            // 이벤트 처리
            MemberMappingToProjectRequestDto memberMappingToProjectRequestDto = MemberMappingToProjectRequestDto.builder()
                    .userId(event.getUserId())
                    .projectId(event.getProjectId())
                    .build();
            memberService.memberAddToProject(memberMappingToProjectRequestDto);
            // 처리 로그 출력
            log.info("Processed CreateMemberAtProjectEvent for userId: " + memberMappingToProjectRequestDto.getUserId());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
