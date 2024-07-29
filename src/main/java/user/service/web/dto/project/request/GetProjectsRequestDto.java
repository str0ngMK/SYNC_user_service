package user.service.web.dto.project.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "해당 유저가 속한 프로젝트를 가져오는 DTO")
public class GetProjectsRequestDto {
    @Schema(description = "유저 로그인 아이디")
    @NotBlank
    String userId;
}
