package user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.service.entity.Authentication;
import user.service.entity.InfoSet;
import user.service.entity.Role;
import user.service.entity.User;
import user.service.oauth2.CustomOAuth2User;
import user.service.oauth2.dto.OAuth2UserDto;
import user.service.oauth2.dto.response.OAuth2GoogleResponseDto;
import user.service.oauth2.dto.response.OAuth2KakaoResponseDto;
import user.service.oauth2.dto.response.OAuth2NaverResponseDto;
import user.service.oauth2.dto.response.OAuth2Response;
import user.service.repository.AuthenticationRepository;
import user.service.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuth2UserSerivce extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        InfoSet infoSet;
        log.info("OAuth2User : " + oAuth2User);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            infoSet = InfoSet.NAVER;
            oAuth2Response = new OAuth2NaverResponseDto(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            infoSet = InfoSet.GOOGLE;
            oAuth2Response = new OAuth2GoogleResponseDto(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {
            infoSet = InfoSet.KAKAO;
            oAuth2Response = new OAuth2KakaoResponseDto(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String userId = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
        Authentication existData = authenticationRepository.findByUserId(userId);

        if (existData == null) {
            Authentication authentication = Authentication.builder()
                    .userId(userId)
                    .email(oAuth2Response.getEmail())
                    .infoSet(infoSet)
                    .build();
            authenticationRepository.save(authentication);
            User user = User.builder()
                    .username(oAuth2Response.getName())
                    .role(Role.USER)
                    .build();
            user.setAuthentication(authentication);
            authentication.setUser(user);
            userRepository.save(user);

            OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
                    .name(oAuth2Response.getName())
                    .infoSet(infoSet.toString())
                    .username(userId)
                    .role(Role.USER.toString())
                    .build();

            return new CustomOAuth2User(oAuth2UserDto);
        }
        else {
            OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
                    .name(oAuth2Response.getName())
                    .infoSet(infoSet.toString())
                    .username(existData.getUserId())
                    .role(Role.USER.toString())
                    .build();

            return new CustomOAuth2User(oAuth2UserDto);
        }
    }
}
