package com.cmc.board.comment.controller;

import com.cmc.board.comment.controller.dto.CommentCreateRequest;
import com.cmc.board.comment.domain.Comment;
import com.cmc.board.comment.service.CommentService;
import com.cmc.board.global.common.SessionConst;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 댓글 / 대댓글 생성
     */
    @PostMapping
    public Comment create(@PathVariable Long postId,
                          @RequestBody CommentCreateRequest request,
                          HttpSession session) {

        Long loginUserId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (loginUserId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        return commentService.create(
                postId,
                loginUserId,
                request.getParentCommentId(),
                request.getContent()
        );
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long postId,
                       @PathVariable Long commentId,
                       HttpSession session) {

        Long loginUserId = (Long) session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (loginUserId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        commentService.delete(commentId, loginUserId);
    }
}
