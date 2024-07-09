package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import user.service.web.dto.project.request.UpdateProjectRequestDto;

@AllArgsConstructor
@Getter
public class ProjectUpdateEvent {
    private UpdateProjectRequestDto projectUpdateRequestDto;
}
