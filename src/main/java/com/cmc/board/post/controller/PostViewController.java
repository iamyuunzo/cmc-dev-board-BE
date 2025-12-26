package com.cmc.board.post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/posts")
public class PostViewController {

    @GetMapping
    public String list(Model model) {
        model.addAttribute("posts", List.of(
                Map.of("id", 1L, "title", "첫 번째 글"),
                Map.of("id", 2L, "title", "두 번째 글")
        ));
        return "post/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Map<String, Object> post = new HashMap<>();
        post.put("id", id);
        post.put("title", "제목 예시 - " + id);
        post.put("content", "내용 예시입니다. 상세 페이지 연결 성공!");

        model.addAttribute("post", post);
        return "post/detail";
    }

    @GetMapping("/new")
    public String createForm() {
        return "post/create";
    }

    @PostMapping
    public String create() {
        // 🔥 지금은 실제 저장 안 함 (임시)
        return "redirect:/posts";
    }

}
