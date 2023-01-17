package com.jojoldu.book.initCK.web;

import com.jojoldu.book.initCK.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Json 반환 컨트롤러로 변환. 
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name, // 외부 API로 넘긴 파라미터 가져오는 이노테이션.
                                             @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}



