package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import user.service.web.dto.project.request.CreateProjectRequestDto;
@AllArgsConstructor
@Getter
public class ProjectCreateEvent{
    private CreateProjectRequestDto projectCreateRequestDto;
    private String userId;
}
