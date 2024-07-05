package user.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",updatable = false)
    private User user;
    private Long projectId;
    private Boolean isManager;
}
