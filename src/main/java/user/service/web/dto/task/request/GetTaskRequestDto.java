package user.service.web.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Schema(description = "해당 업무의 하위 업무를 가져오기 위한 DTO")
public class GetTaskRequestDto {
    @Schema(description = "업무 아이디")
    @NotBlank
    private Long taskId;
}
