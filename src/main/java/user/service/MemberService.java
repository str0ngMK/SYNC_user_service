package user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.entity.Member;
import user.service.entity.User;
import user.service.global.advice.ResponseMessage;
import user.service.global.exception.EntityNotFoundException;
import user.service.global.exception.MemberDuplicateInProjectException;
import user.service.repository.MemberRepository;

import java.util.List;

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

        String baseUrl = "http://localhost:8070/project/api/v1/find";
        String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("projectId", projectId)
                .toUriString();
        ResponseMessage responseMessage = webClient.build()
                .get()
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
     * 멤버 존재 여부 확인
     * @param MemberId
     * @return
     */
    @Transactional(rollbackFor = { Exception.class })
    public Member findMember(Long MemberId) {
        Member member = memberRepository.findById(MemberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with ID: " + MemberId));
        memberRepository.save(member);
        return member;
    }
}
