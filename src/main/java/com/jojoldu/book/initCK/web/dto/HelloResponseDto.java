package com.jojoldu.book.initCK.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성. (final 없는 필드는 생성자에 포함되지 않음.)
public class HelloResponseDto {

    private final String name;
    private final int amount;

    // lombok이 동작하지 않을 때, 직접 getter 작성해줘야함.
        //    public HelloResponseDto(String name, int amount){
        //        this.name = name;
        //        this.amount = amount;
        //    }

        //    public int getAmount() {
        //        return amount;
        //    }
        //
        //    public String getName() {
        //        return name;
        //    }
}
