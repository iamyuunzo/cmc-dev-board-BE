package com.cmc.board.post.service;

import com.cmc.board.post.domain.Post;
import com.cmc.board.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PostService 유닛 테스트
 *
 * BDD 스타일 (Given / When / Then)
 */
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글을 생성하면 정상적으로 저장된다")
    void createPost_success() {
        // ===== Given =====
        User user = new User(
                "test@test.com",
                "password",
                "USER",
                LocalDateTime.now()
        );

        String title = "테스트 게시글 제목";
        String content = "테스트 게시글 내용";

        // ===== When =====
        Post post = postService.create(user, title, content);

        // ===== Then =====
        assertThat(post.getId()).isNotNull();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getAuthor()).isNotNull();
    }
}
