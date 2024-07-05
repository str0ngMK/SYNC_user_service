package user.service.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Schema(description = "회원가입 요청 DTO")
public class SignupRequestDto {
    @Schema(description = "사용자 이름")
    private String username;
    
    @Schema(description = "사용자 닉네임")
    private String nickname;
    
    @Schema(description = "사용자 로그인 아이디")
//    @Size(min = 8, max = 16)
    @Size(min = 5, max = 30)
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userId;
    
    @Schema(description = "사용자 비밀번호")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
    
    @Schema(description = "사용자 이메일")
//    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;
}
