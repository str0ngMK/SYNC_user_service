package user.service.dto.alarm.response;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import user.service.entity.User;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AlarmUrlDto {
	private UUID url;
	private User user;
	
	@Builder 
	public AlarmUrlDto(UUID url, User user) {
		this.url=url;
		this.user=user;
	}
	
//
//	public AlarmUrl toEntity() {
//		return AlarmUrl.builder()
//				.url(url)
//				.user(user)
//				.build();
//	}
	

}
