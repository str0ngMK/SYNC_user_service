package user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.service.entity.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
    Authentication findByUserId(String userId);
}
