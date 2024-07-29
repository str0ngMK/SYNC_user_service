package user.service.web.dto.project.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "프로젝트를 삭제하기 위한 DTO")
public class DeleteProjectRequestDto {
    @Schema(description = "프로젝트 아이디")
    @NotNull(message = "프로젝트 아이디는 필수 입력 값 입니다.")
    private Long projectId;
}
