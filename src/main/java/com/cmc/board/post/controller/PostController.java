package com.cmc.board.post.controller;

import com.cmc.board.post.controller.dto.CreatePostRequest;
import com.cmc.board.post.domain.Post;
import com.cmc.board.post.service.PostService;
import com.cmc.board.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody CreatePostRequest request) {

        User author = getTemporaryUserForTest();

        Post post = postService.create(
                author,
                request.getTitle(),
                request.getContent()
        );

        return ResponseEntity.ok(post);
    }

    /**
     * 임시 사용자 반환 메서드
     *
     * - Step 3~5 단계에서 테스트를 위해 사용
     * - 추후 세션/인증 로직으로 대체 예정
     */
    private User getTemporaryUserForTest() {
        return new User(
                "test@test.com",
                "password",
                "USER",
                LocalDateTime.now()
        );
    }
}
