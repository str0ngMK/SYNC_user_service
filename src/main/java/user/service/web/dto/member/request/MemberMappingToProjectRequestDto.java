package user.service.web.dto.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "멤버를 프로젝트에 생성하기 위한 DTO")
public class MemberMappingToProjectRequestDto {
    @Schema(description = "유저 로그인 아이디")
    @NotBlank(message = "유저 아이디는 필수값입니다.")
    private List<String> userIds;
    @Schema(description = "프로젝트 아이디, 해당 프로젝트가 존재하는지 확인하세요")
    @NotBlank(message = "프로젝트 아이디는 필수값입니다.")
    private Long projectId;
    @Schema(description = "해당 유저의 관리자 지정 여부")
    private int isManager;
}
