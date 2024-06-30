package user.service.oauth2.dto.response;

import java.util.Map;

public class OAuth2KakaoResponseDto implements OAuth2Response{
    private final Map<String, Object> attribute;
    public OAuth2KakaoResponseDto(Map<String, Object> attribute) {

        this.attribute = attribute;
        System.out.println("kakaoResDto : " + (Map<String, Object>) attribute.get("kakao_account"));
    }
    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> temp = (Map<String, Object>) attribute.get("kakao_account");
        return temp.get("email").toString();
    }

    @Override
    public String getName() {
        Map<String, Object> temp = (Map<String, Object>) attribute.get("kakao_account");
        return temp.get("name").toString();
    }
}
