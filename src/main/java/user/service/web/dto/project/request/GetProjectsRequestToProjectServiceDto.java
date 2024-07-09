package user.service.web.dto.project.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetProjectsRequestToProjectServiceDto {
    private List<Long> projectIds;
}