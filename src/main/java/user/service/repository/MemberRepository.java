package user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import user.service.entity.Member;
import user.service.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m WHERE m.user.id = :userId AND m.projectId = :projectId")
    Optional<Member> findMemberByUserIdAndProjectId(@Param("userId") Long userId, @Param("projectId") Long projectId);
    List<Member> findByProjectId(Long projectId);
    @Query("SELECT m.projectId FROM Member m WHERE m.user.id = :userId")
    List<Long> findProjectIdsByUserId(@Param("userId") Long userId);
    @Query("SELECT m.id FROM Member m WHERE m.projectId = :projectId")
    List<Long> findMemberIdsByProjectId(@Param("projectId") Long projectId);
    List<Member> findByUserId(Long userId);
}
