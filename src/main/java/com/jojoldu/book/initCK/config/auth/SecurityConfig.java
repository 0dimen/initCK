package com.jojoldu.book.initCK.config.auth;
//
import com.jojoldu.book.initCK.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@RequiredArgsConstructor
//@EnableWebSecurity //Spring Security 활성화
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final CustomOAuth2UserService customOAuth2UserService;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .headers().frameOptions().disable() // h2-console 화면 사용을 위해 해당 옵션 비활성화.
//            .and()
//                .authorizeRequests() // URL별 권한 관리 설정. antMatchers 옵션 사용 가능.
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/profile").permitAll() // 권한 관리 대상 지정 옵션. permitAll 전체 열람 가능.
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // 권한 관리 대상 지정 옵션. USER 권한을 가진 자만 해당 api 열람 가능.
//                .anyRequest().authenticated() // 나머지 URL은 인증된(로그인 된) 모든 사용자들에게 허용.
//            .and()
//                .logout()
//                    .logoutSuccessUrl("/") //로그아웃 성공 시, /로 이동
//            .and()
//                .oauth2Login() // Oauth2 로그인 기능에 대한 설정 진입점.
//                    .userInfoEndpoint() //로그인 성공 시, 사용자 정보 가져올 때 설정 담당.
//                        .userService(customOAuth2UserService); // 소셜 로그인 성공 시, UserService 인터페이스의 구현체 등록.
//        super.configure(http);
//    }
//}


@RequiredArgsConstructor
@EnableWebSecurity // 스프링 security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}