package user.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id", "user_id"})
})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",updatable = false, nullable = false)
    private User user;
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    @Column(name = "is_manager", nullable = false)
    private int isManager;
}
