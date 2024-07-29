package user.service.web.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "해당 업무를 삭제하기 위한 DTO")
public class DeleteTaskRequestDto {
    @Schema(description = "업무 아이디")
    @NotEmpty
    private Long taskId;
    @Schema(description = "삭제할 업무의 프로젝트 아이디")
    @NotEmpty(message = "프로젝트 아이디는 필수 입력 값 입니다.")
    private Long projectId;
}
