package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateMemberAtProjectEvent {
    String projectId;
    String userId;
}
