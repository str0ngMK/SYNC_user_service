package user.service.web.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "프로젝트 멤버에 업무를 할당하기 위한 DTO")
public class MemberMappingToTaskRequestDto {
    @Schema(description = "멤버 아이디, 멤버는 모두 같은 프로젝트 소속이어야 합니다.")
    private List<Long> memberIds;
    @Schema(description = "업무 아이디")
    private Long taskId;
}
