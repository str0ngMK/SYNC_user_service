package user.service.web.dto.project.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class GetProjectsFromProjectServiceResponseDto {
    private Long projectId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;

}
