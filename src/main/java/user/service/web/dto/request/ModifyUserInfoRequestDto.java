package user.service.web.dto.request;

import lombok.Data;

@Data
public class ModifyUserInfoRequestDto {
	private String type;
	private String value;
}
