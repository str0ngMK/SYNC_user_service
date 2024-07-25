package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserAddToProjectEvent {
    private Long projectId;
    private String userId;
}
