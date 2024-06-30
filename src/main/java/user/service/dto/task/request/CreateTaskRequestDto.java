package user.service.dto.task.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Setter
@Getter
@Schema(description = "업무를 생성하기 위한 DTO")
public class CreateTaskRequestDto {
    @Schema(description = "업무 내용")
    private String description;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @Schema(description = "업무 종료일")
    private Date endDate;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @Schema(description = "업무 시작일")
    private Date startDate;
    @NotBlank(message = "상태는 필수 입력 값 입니다.")
    @Schema(description = "업무 상태")
    private Boolean status;
    @NotBlank(message = "이름은 필수 입력 값 입니다.")
    @Schema(description = "업무 이름")
    private String title;
    @Schema(description = "상위 업무 아이디, null == 프로젝트 최상위 업무")
    private Optional<Long> parentTaskId;
    @Schema(description = "프로젝트 아이디")
    @NotBlank(message = "프로젝트는 필수 입력 값 입니다.")
    private Long projectId;

}
