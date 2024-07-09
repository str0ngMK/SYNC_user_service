package user.service.kafka.task.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class TaskDeleteEvent {
    Long taskId;
}
