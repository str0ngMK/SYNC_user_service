package user.service.web.dto.project.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateProjectRequestDto {
    private Long projectId;
    private String description;
    private String title;
    private String subTitle;
    private Date startDate;
    private Date endDate;
}