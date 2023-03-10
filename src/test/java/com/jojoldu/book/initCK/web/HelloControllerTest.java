package com.jojoldu.book.initCK.web;

import com.jojoldu.book.initCK.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)//스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
}) // 웹에 집중할 수 있는 어노테이션(주석). @Controller, @ControllerAdvice 사용 가능하게 함.
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 Bean을 주입받는다. ??
    private MockMvc mvc; // 웹 API 테스트용. GET, POST등의 API 테스트 가능.

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청.
                .andExpect(status().isOk()) // mvc.perform 결과(HTTP Header의 Status)가 200인지 아닌지 검증.(ex. 200, 404, 500 등)
                .andExpect(content().string(hello));// mvc.perform의 응답 본문 내용을 검증. Controller에서 hello를 리턴하는지 확인.
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                MockMvcRequestBuilders.get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());
    }
}
