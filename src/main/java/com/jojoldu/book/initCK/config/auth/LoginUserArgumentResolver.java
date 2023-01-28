package com.jojoldu.book.initCK.config.auth;

import com.jojoldu.book.initCK.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 컨트롤러 메소드의 특정 파라미터를 지원하는지 확인.
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; // 파라미터에 로그인 어노테이션이 붙어있는지.
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // 파라미터 클래스 타입이 Session.class인지.

        return isLoginUserAnnotation && isUserClass; // 위 두 가지가 참인 경우, true 반환
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception { // 파라미터에 전달할 객체 생성. 세션에서 가져옴
        return httpSession.getAttribute("user");
    }
}
