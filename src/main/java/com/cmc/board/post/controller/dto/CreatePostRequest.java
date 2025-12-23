package com.cmc.board.post.controller.dto;

/**
 * 게시글 생성 요청 DTO
 */
public class CreatePostRequest {

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
