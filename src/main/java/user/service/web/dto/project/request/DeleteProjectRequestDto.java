package user.service.web.dto.project.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "프로젝트를 삭제하기 위한 DTO")
public class DeleteProjectRequestDto {
    @Schema(description = "프로젝트 아이디")
    private Long projectId;
}
