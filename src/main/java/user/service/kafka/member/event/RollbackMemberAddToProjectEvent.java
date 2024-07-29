package user.service.kafka.member.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RollbackMemberAddToProjectEvent {

    Long projectId;
    List<String> userIds;
}
