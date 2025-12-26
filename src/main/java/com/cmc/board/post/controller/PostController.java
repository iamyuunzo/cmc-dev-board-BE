package com.cmc.board.post.controller;

import com.cmc.board.global.common.SessionConst;
import com.cmc.board.post.controller.dto.CreatePostRequest;
import com.cmc.board.post.domain.Post;
import com.cmc.board.post.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시글 생성 (로그인 필수)
     */
    @PostMapping
    public Post create(@RequestBody CreatePostRequest request,
                       HttpSession session) {

        Long loginUserId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (loginUserId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        return postService.create(loginUserId, request.getTitle(), request.getContent());
    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{id}")
    public Post findOne(@PathVariable Long postId) {
        return postService.findById(postId);
    }

    /**
     * 게시글 삭제 (작성자만 가능)
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long postId,
                       HttpSession session) {

        Long loginUserId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (loginUserId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        postService.delete(postId, loginUserId);
    }
}