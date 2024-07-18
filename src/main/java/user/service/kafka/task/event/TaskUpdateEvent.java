package user.service.kafka.task.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import user.service.web.dto.task.request.UpdateTaskRequestDto;

@AllArgsConstructor
@Getter
public class TaskUpdateEvent {
    UpdateTaskRequestDto updateTaskRequestDto;
}
