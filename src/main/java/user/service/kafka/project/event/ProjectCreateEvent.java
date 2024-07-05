package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import user.service.web.dto.project.request.CreateProjectRequestDto;

@AllArgsConstructor
public class ProjectCreateEvent {
    private final CreateProjectRequestDto projectCreateRequestDto;
    private final String userId;
}
