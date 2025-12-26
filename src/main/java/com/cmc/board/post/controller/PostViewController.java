package com.cmc.board.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostViewController {

    @GetMapping
    public String list(Model model) {
        // ✅ 임시 데이터(컴파일 100% 통과)
        model.addAttribute("posts", List.of(
                new SimplePost(1L, "첫 번째 글"),
                new SimplePost(2L, "두 번째 글")
        ));
        return "post/list";
    }

    // ✅ 화면용 임시 DTO (내부 클래스)
    public record SimplePost(Long id, String title) {}
}
