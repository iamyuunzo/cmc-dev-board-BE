package com.cmc.board.post.controller;

import com.cmc.board.post.domain.Post;
import com.cmc.board.post.service.PostService;
import com.cmc.board.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 게시글 API Controller
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시글 생성 API (임시 버전)
     *
     * - 로그인/세션 구현 전이라
     *   User를 임의로 생성해서 사용
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest request) {

        // ⚠️ 임시 사용자 (나중에 세션에서 꺼낼 예정)
        User tempUser = new User(
                "test@test.com",
                "password",
                "USER",
                LocalDateTime.now()
        );

        Post post = postService.create(
                tempUser,
                request.getTitle(),
                request.getContent()
        );

        return ResponseEntity.ok(post);
    }

    /**
     * 게시글 생성 요청 DTO
     * - Step 3에서는 Controller 내부 static 클래스로 둬도 OK
     */
    static class CreatePostRequest {

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
