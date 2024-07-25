package user.service.web.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Schema(description = "업무의 담당자를 가져오기 위한 DTO")
public class GetMemberFromTaskRequestDto {
    @Schema(description = "업무 아이디")
    @NotBlank(message = "업무 아이디는 필수 입력 값 입니다.")
    private Long taskId;
}
