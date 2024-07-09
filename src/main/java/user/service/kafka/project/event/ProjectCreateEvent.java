package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import user.service.web.dto.project.request.CreateProjectRequestDto;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ProjectCreateEvent{
    private final CreateProjectRequestDto projectCreateRequestDto;
    private final String userId;
}
