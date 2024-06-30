package user.service.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "해당 업무를 삭제하기 위한 DTO")
public class DeleteTaskRequestDto {
    @Schema(description = "업무 아이디")
    @NotBlank
    private Long taskId;
    @Schema(description = "프로젝트 아이디")
    @NotBlank
    private Long projectId;
}
