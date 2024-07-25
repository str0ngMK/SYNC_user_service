package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAddToProjectEvent {
    private Long projectId;
    private String userId;
}
