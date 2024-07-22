package user.service.web.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Schema(description = "업무의 담당자를 가져오기 위한 DTO")
public class GetMemberFromTaskRequestDto {
    @Schema(description = "업무 아이디")
    private Long taskId;
}
