package user.service.kafka.task.event;
import user.service.web.dto.task.request.CreateTaskRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TaskCreateEvent {
    private CreateTaskRequestDto createTaskRequestDto;
}
