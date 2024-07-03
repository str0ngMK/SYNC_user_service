package user.service.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaHost;
	
	public String getKafkaHost() {
		return this.kafkaHost;
	}
}
