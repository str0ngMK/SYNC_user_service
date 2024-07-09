package user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.global.exception.InvalidValueException;
import user.service.global.exception.MemberDuplicateInTaskException;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.entity.Member;
import user.service.entity.User;
import user.service.global.advice.ResponseMessage;
import user.service.global.exception.EntityNotFoundException;
import user.service.global.exception.MemberDuplicateInProjectException;
import user.service.repository.MemberRepository;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserService userService;
    private final WebClient.Builder webClient;
    /**
     * 프로젝트에 멤버를 추가합니다.
     * @param memberMappingToProjectRequestDto
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public ResponseMessage memberAddToProject(MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
        List<String> userIds = memberMappingToProjectRequestDto.getUserIds();
        Long projectId = memberMappingToProjectRequestDto.getProjectId();
        Boolean isManager = memberMappingToProjectRequestDto.getIsManager();
        //http://129.213.161.199:31585/project/api/v1/find
        //http://localhost:8070/project/api/v1/find
        String baseUrl = "http://129.213.161.199:31585/project/api/v1/find";
        String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("projectId", projectId)
                .toUriString();
        ResponseMessage responseMessage = webClient.build()
                .post()
                .uri(urlWithQueryParam)
                .retrieve()
                .bodyToMono(ResponseMessage.class)
                .block();
        // 프로젝트 존재 여부 확인
        if (!responseMessage.isResult()) {
            return new ResponseMessage("프로젝트가 존재하지 않습니다.", false, memberMappingToProjectRequestDto.getProjectId());
        }

        userIds.forEach(userId -> {
            try {
                User user = userService.findUserEntity(userId);
                Member member = Member.builder()
                        .isManager(isManager)
                        .projectId(projectId)
                        .user(user)
                        .build();
                memberRepository.save(member);
            } catch (DataIntegrityViolationException e) {
                throw new MemberDuplicateInProjectException(e.getMessage());
            }
        });
        return new ResponseMessage("멤버 추가 성공", true, userIds);
    }
    /**
     * 프로젝트에 속한 멤버를 조회합니다.
     * @param projectId, userId
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public Member findMemberByUserIdAndProjectId(Long userId, Long projectId) {
        return memberRepository.findMemberByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with UserId: " + userId + " and ProjectId: " + projectId));
    }
    /**
     * 멤버가 관리자인지 확인합니다.
     * @param memberId
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public void isManager(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (!member.get().getIsManager()) {
            throw new InvalidValueException("해당 멤버는 관리자가 아닙니다.");
        }
    }
    /**
     * 모든 멤버가 같은 프로젝트에 소속되어 있는지 확인합니다.
     * @param memberMappingToTaskRequestDto
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public ResponseMessage allMembersInSameProject(MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
        List<Long> memberIds = memberMappingToTaskRequestDto.getMemberIds();
        Set<Long> uniqueProjectIds = memberIds.stream()
                .map(memberId -> memberRepository.findById(memberId)
                        .orElseThrow(() -> new EntityNotFoundException("Member not found for ID: " + memberId)))
                .map(Member::getProjectId)
                .collect(Collectors.toSet());

        if (uniqueProjectIds.size() == 1) {
            // 모든 멤버가 같은 프로젝트에 속해 있을 경우
            List<Long> userIds = memberIds.stream()
                    .map(memberId -> memberRepository.findById(memberId).get().getUser().getId())
                    .collect(Collectors.toList());
            return new ResponseMessage("모든 멤버가 같은 프로젝트에 속해 있습니다.", true, userIds);
        } else {
            // 멤버들이 서로 다른 프로젝트에 속해 있을 경우
            return new ResponseMessage("모든 멤버가 같은 프로젝트에 속해 있지 않습니다.", false, memberIds);
        }
    }
    /**
     * 프로젝트에 속해있는 멤버들을 삭제합니다.
     * @param projectId
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public void deleteMembersByProjectId(Long projectId) {
        List<Member> members = memberRepository.findByProjectId(projectId);
        memberRepository.deleteAll(members);
    }
    /**
     * 사용자가 속한 프로젝트 ID를 조회합니다.
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public List<Long> getProjectIdsByUserId(Long userId) {
        return memberRepository.findProjectIdsByUserId(userId);
    }
    /**
     * 프로젝트에 속한 멤버 ID를 조회합니다.
     * @param projectId
     * @return
     */
    public List<Long> getMemberIdsByProjectId(Long projectId) {
        return memberRepository.findMemberIdsByProjectId(projectId);
    }
}
