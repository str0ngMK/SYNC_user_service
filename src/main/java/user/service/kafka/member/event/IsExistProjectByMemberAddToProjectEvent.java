package user.service.kafka.member.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class IsExistProjectByMemberAddToProjectEvent {
    Long projectId;
    List<String> userIds;
}
