package user.service.web.dto.project.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor
@Schema(description = "프로젝트 멤버들의 UserId를 가져오는 RESPONSE DTO")
public class GetUserIdsByProjectsResponseDto {
    Long ProjectId;
    List<Long> userIds;
}
