package user.service.web.dto.project.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Schema(description = "해당 유저가 속한 프로젝트를 가져오는 RESPONSE DTO")
public class GetProjectsResponseDto {
    private Long projectId;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    @Schema(description = "user entity의 id를 반환")
    private List<Long> memberIds = new ArrayList<>();
    public GetProjectsResponseDto(Long projectId, String title, String description, String startDate, String endDate, List<Long> memberIds) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberIds = memberIds;
    }
}
