package com.jojoldu.book.initCK.config.auth;

import com.jojoldu.book.initCK.config.auth.dto.SessionUser;
import com.jojoldu.book.initCK.domain.user.User;
import com.jojoldu.book.initCK.domain.user.UserRepository;
import com.jojoldu.book.initCK.config.auth.dto.OAuthAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserRepository userRepository;
//    private final HttpSession httpSession;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId(); //로그인 진행 중인 서비스 구분 코드. (ex. 구글인지 네이버인지, 혹은 카카오인지)
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // 로그인 시 key가 되는 필드. (네이버 or 카카오 로그인과 구글 로그인을 동시 지원할 때 사용됨.)
//
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes()); // OAuth2User의 attribute를 담을 클래스. 네이버 등 이 클래스 사용.
//
//        User user = saveOrUpdate(attributes);
//
//        httpSession.setAttribute("user", new SessionUser(user));
//
//        return new DefaultOAuth2User(Collections.singleton(new
//                SimpleGrantedAuthority(user.getRolekey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }
//
//    private User saveOrUpdate(OAuthAttributes attributes){
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                .orElse(attributes.toEntity());
//
//        return userRepository.save(user);
//    }
//}

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRolekey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}