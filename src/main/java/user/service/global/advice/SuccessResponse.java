package user.service.global.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SuccessResponse {
	private String message;
	
	@Builder.Default
	private boolean result = true;
	
	private Object data;
}
