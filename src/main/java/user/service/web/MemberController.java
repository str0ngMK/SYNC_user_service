package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.MemberService;
import user.service.global.advice.ResponseMessage;
import user.service.kafka.task.KafkaTaskProducerService;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;

@RestController
@RequestMapping("user/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KafkaTaskProducerService kafkaTaskProducerService;
    @PostMapping("/project/add")
    public ResponseMessage memberAddToProject(@RequestBody MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
        return memberService.memberAddToProject(memberMappingToProjectRequestDto);
    }

    @PostMapping("/task/add")
    public ResponseMessage userAddToTask(@RequestBody MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
        return kafkaTaskProducerService.sendAddUserToTaskEvent(memberMappingToTaskRequestDto);
    }
}
