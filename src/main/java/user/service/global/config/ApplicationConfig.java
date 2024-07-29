package user.service.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaHost;
	
	@Value("${api.server.alarm}")
	private String alarmApi;
	
	@Value("${api.server.project}")
	private String projectApi;
	
	public String getKafkaHost() {
		return this.kafkaHost;
	}
	
	public String getAlarmApi() {
		return this.alarmApi;
	}
	
	public String getProjectApi() {
		return this.projectApi;
	}
}
