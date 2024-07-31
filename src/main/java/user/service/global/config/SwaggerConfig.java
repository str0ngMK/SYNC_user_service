package user.service.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI openAPI() {
        Info info = new Info()
				.title("SYNC API")
				.description("SYNC API Description")
				.contact(new Contact()
						.name("MinKyeong and HyunWoong")
						.url("https://github.com/str0ngMK/Hinc_BE")
						.email("qldrhsmszz@naver.com, 9905hyun@naver.com")
				);
		return new OpenAPI().components(new Components()).info(info);
	}
}
