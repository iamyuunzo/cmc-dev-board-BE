package com.cmc.board.post.domain;

import com.cmc.board.global.error.ForbiddenException;
import com.cmc.board.user.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 게시글 엔티티
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Post() {}

    private Post(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * 🔥 정적 팩토리 메서드
     * - 생성 의도를 명확히 드러냄
     */
    public static Post create(User author, String title, String content) {
        return new Post(author, title, content);
    }

    /**
     * 작성자 검증 (도메인 규칙)
     */
    public void validateAuthor(Long loginUserId) {
        if (!author.getId().equals(loginUserId)) {
            throw new ForbiddenException("작성자만 수정/삭제할 수 있습니다.");
        }
    }

    // Getter
    public Long getId() { return id; }
    public User getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
