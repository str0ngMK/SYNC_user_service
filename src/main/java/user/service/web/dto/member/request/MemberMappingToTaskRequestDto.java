package user.service.web.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "프로젝트 멤버에 업무를 할당하기 위한 DTO")
public class MemberMappingToTaskRequestDto {
    @Schema(description = "멤버 아이디, 멤버는 모두 같은 프로젝트 소속이어야 합니다.")
    @NotBlank(message = "멤버 아이디는 필수값입니다.")
    private List<Long> memberIds;
    @Schema(description = "업무 아이디")
    @NotBlank(message = "업무 아이디는 필수값입니다.")
    private Long taskId;
}
