package user.service.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
////	@Override
////    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        registry.enableSimpleBroker("/alarm");
////        registry.setApplicationDestinationPrefixes("/app");
////    }
////
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        registry.addEndpoint("/api/user/ws").withSockJS();
////        registry.addEndpoint("/ws").withSockJS();
////    }
////
////    @Bean
////    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
////        return new MappingJackson2MessageConverter();
////    }
//}
