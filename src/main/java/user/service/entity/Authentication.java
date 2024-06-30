package user.service.entity;
import jakarta.persistence.*;
import lombok.*;
import user.service.global.entity.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authentication")
public class Authentication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authentication_id")
    private Long id;
    @Column(unique = true, name = "user_login_id")
    private String userId;
    private String password;
    private String email;
    private Integer failCount;
    @Enumerated(EnumType.STRING)
    private InfoSet infoSet;
    @OneToOne(mappedBy = "authentication")
    private User user;
}
