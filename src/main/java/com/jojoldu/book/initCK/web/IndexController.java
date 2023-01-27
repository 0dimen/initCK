package com.jojoldu.book.initCK.web;

import com.jojoldu.book.initCK.config.auth.dto.SessionUser;
import com.jojoldu.book.initCK.service.PostsService;
import com.jojoldu.book.initCK.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model ) {
        model.addAttribute("posts", postsService.findAllDesc());
        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 로그인 성공 시, httpSession.getAttribute에서 값을 가져올 수 있음.

        if(user != null){
            model.addAttribute("userName", user.getName()); // 세션에 저장된 값이 있을 때, model에 userName으로 저장.
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return ("posts-update");
    }
}
