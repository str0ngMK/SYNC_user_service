package user.service.dto.task.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.scheduling.config.Task;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "해당 업무의 하위 업무를 반환하는 DTO")
public class GetTaskResponseDto {
    @Schema(description = "업무 아이디")
    private Long id;
    @Schema(description = "업무 이름")
    private String title;
    @Schema(description = "업무 내용")
    private String description;
    @Schema(description = "업무 시작일")
    private Date startDate;
    @Schema(description = "업무 종료일")
    private Date endDate;
    @Schema(description = "업무 진행 상황")
    private Boolean status;
    @Schema(description = "업무 담당자")
    private Long memberId;
    @Schema(description = "하위 업무들")
    private List<GetTaskResponseDto> subTasks;
//
//    public static GetTaskResponseDto fromEntity(Task task) {
//        List<GetTaskResponseDto> subTaskDtos = task.getSubTasks().stream()
//                .map(GetTaskResponseDto::fromEntity)
//                .collect(Collectors.toList());
//
//        return GetTaskResponseDto.builder()
//                .id(task.getId())
//                .title(task.getTitle())
//                .description(task.getDescription())
//                .startDate(task.getStartDate())
//                .endDate(task.getEndDate())
//                .status(task.getStatus())
//                .subTasks(subTaskDtos)
//                .build();
//    }
//    public static GetTaskResponseDto fromEntityOnlyChildrenTasks(Task task) {
//        return GetTaskResponseDto.builder()
//                .id(task.getId())
//                .title(task.getTitle())
//                .description(task.getDescription())
//                .startDate(task.getStartDate())
//                .endDate(task.getEndDate())
//                .status(task.getStatus())
//                .subTasks(task.getSubTasks().stream()
//                        .map(child -> GetTaskResponseDto.builder()
//                                .id(child.getId())
//                                .title(child.getTitle())
//                                .description(child.getDescription())
//                                .startDate(child.getStartDate())
//                                .endDate(child.getEndDate())
//                                .status(child.getStatus())
//                                // 자식의 자식 업무를 추가하지 않습니다.
//                                .build())
//                        .collect(Collectors.toList()))
//                .build();
//    }

}