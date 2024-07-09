package user.service.kafka.task.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import user.service.web.dto.member.request.MemberMappingToTaskRequestDto;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserAddToTaskEvent {
    private List<Long> userIds;
    private Long taskId;


}
