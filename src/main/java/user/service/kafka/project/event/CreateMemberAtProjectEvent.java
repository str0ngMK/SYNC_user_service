package user.service.kafka.project.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CreateMemberAtProjectEvent{
    Long projectId;
    String userId;
}
