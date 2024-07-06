package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.MemberService;
import user.service.global.advice.ResponseMessage;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;

@RestController
@RequestMapping("user/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/project/add")
    public ResponseEntity<ResponseMessage> memberAddToProject(@RequestBody MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
        return ResponseEntity.ok().body(memberService.memberAddToProject(memberMappingToProjectRequestDto));
    }
//
//    @PostMapping("/task/add")
//    public ResponseEntity<ResponseMessage> memberAddToTask(@RequestBody MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
//        return ResponseEntity.ok().body(memberService.memberAddToTask(memberMappingToTaskRequestDto));
//    }
}
