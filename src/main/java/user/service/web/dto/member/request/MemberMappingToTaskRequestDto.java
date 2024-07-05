package user.service.web.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "프로젝트 멤버에 업무를 할당하기 위한 DTO")
public class MemberMappingToTaskRequestDto {
    @Schema(description = "멤버 아이디")
    private Long memberId;
    @Schema(description = "업무 아이디")
    private Long taskId;
}
