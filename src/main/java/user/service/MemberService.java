package user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.entity.Member;
import user.service.entity.User;
import user.service.global.advice.ResponseMessage;
import user.service.global.exception.EntityNotFoundException;
import user.service.global.exception.MemberDuplicateInProjectException;
import user.service.global.exception.UserNotFoundException;
import user.service.repository.MemberRepository;
import user.service.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    /**
     * 프로젝트에 멤버를 추가합니다.
     * @param memberMappingToProjectRequestDto
     * @return
     */
 @Transactional(rollbackFor = { Exception.class })
    public ResponseMessage memberAddToProject(MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
        try {
            String UserId = memberMappingToProjectRequestDto.getUserId();
            //user service로 이관할 것
            User user = Optional.ofNullable(userRepository.findByAuthenticationUserId(memberMappingToProjectRequestDto.getUserId()))
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + UserId));
            Member member = Member.builder().projectId(memberMappingToProjectRequestDto.getProjectId()).isManager(memberMappingToProjectRequestDto.getIsManager())
                    .user(user).build();
            memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new MemberDuplicateInProjectException(e.getMessage());
        }
        return ResponseMessage.builder().message("success").build();
    }
}
