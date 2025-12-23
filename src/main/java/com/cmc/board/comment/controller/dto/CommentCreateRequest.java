package com.cmc.board.comment.controller.dto;

/**
 * 댓글 / 대댓글 생성 요청 DTO
 */
public class CommentCreateRequest {

    private String content;
    private Long parentCommentId; // null이면 일반 댓글

    public String getContent() {
        return content;
    }

    public Long getParentCommentId() {
        return parentCommentId;
    }
}
