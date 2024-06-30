package user.service.entity;

import com.simple.book.domain.task.entity.Task;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_task",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "user_task_uk",
            columnNames = {"user_id", "task_id"}
        )
    }
)
public class UserTask {
    @EmbeddedId
    private UserTaskId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;
}