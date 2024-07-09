package user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import user.service.entity.Member;
import user.service.entity.User;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m WHERE m.user.id = :userId AND m.projectId = :projectId")
    Optional<Member> findMemberByUserIdAndProjectId(@Param("userId") Long userId, @Param("projectId") Long projectId);
//    @Query("SELECT m.project FROM Member m WHERE m.user.id = :userId")
//    List<Project> findProjectsByUserId(@Param("userId") Long userId);
    @Query("SELECT u FROM Member m INNER JOIN m.user u WHERE m.id = :memberId")
    Optional<User> findUserIdByMemberId(@Param("memberId") long memberId);
}
