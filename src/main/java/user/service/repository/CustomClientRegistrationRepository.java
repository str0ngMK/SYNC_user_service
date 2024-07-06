package user.service.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
@Primary
public class CustomClientRegistrationRepository implements ClientRegistrationRepository {
    private final Map<String, ClientRegistration> clientRegistrations = new HashMap<>();

    public CustomClientRegistrationRepository() {
        // 예시: Naver 클라이언트 등록 정보를 생성하고 커스텀 포트를 포함시킵니다.
        ClientRegistration naverRegistration = ClientRegistration.withRegistrationId("naver")
                .clientId("wdyVDz8Boe8hbT6Oy5mT")
                .clientSecret("GEEJgChLfT")
                .redirectUri("http://150.136.153.235:31585/login/oauth2/code/{registrationId}")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                .tokenUri("https://nid.naver.com/oauth2.0/token")
                .userInfoUri("https://openapi.naver.com/v1/nid/me")
                .userNameAttributeName("response")
                .build();

        clientRegistrations.put(naverRegistration.getRegistrationId(), naverRegistration);
    }

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        return clientRegistrations.get(registrationId);
    }
}