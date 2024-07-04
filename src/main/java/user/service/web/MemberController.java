package user.service.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class MemberController {
//    @PostMapping("/project/member/add")
//    public ResponseEntity<ResponseMessage> memberAddToProject(@RequestBody MemberMappingToProjectRequestDto memberMappingToProjectRequestDto) {
//        return ResponseEntity.ok().body(memberService.memberAddToProject(memberMappingToProjectRequestDto));
//    }
//
//    @PostMapping("/task/member/add")
//    public ResponseEntity<ResponseMessage> memberAddToTask(@RequestBody MemberMappingToTaskRequestDto memberMappingToTaskRequestDto) {
//        return ResponseEntity.ok().body(memberService.memberAddToTask(memberMappingToTaskRequestDto));
//    }
}
