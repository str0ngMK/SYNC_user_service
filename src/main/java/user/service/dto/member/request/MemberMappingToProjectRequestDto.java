package user.service.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "멤버를 프로젝트에 할당하기 위한 DTO")
public class MemberMappingToProjectRequestDto {
    @Schema(description = "유저 로그인 아이디")
    private String userId;
    @Schema(description = "프로젝트 아이디")
    private Long projectId;
    @Schema(description = "해당 유저의 관리자 지정 여부")
    private Boolean isManager;
}
