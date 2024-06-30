package user.service.oauth2.dto.response;

import java.util.Map;

public class OAuth2GoogleResponseDto implements OAuth2Response {
    private final Map<String, Object> attribute;

    public OAuth2GoogleResponseDto(Map<String, Object> attribute) {

        this.attribute = attribute;
    }

    @Override
    public String getProvider() {

        return "google";
    }

    @Override
    public String getProviderId() {

        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {

        return attribute.get("email").toString();
    }

    @Override
    public String getName() {

        return attribute.get("name").toString();
    }
}
