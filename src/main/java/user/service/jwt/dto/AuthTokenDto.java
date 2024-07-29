package user.service.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthTokenDto {
    String name;
    String username;
    String role;
    String password;
    String infoSet;

}
