package user.service.web.dto.project.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetProjectsRequestToProjectServiceDto {
    @NotEmpty
    private List<Long> projectIds;
}