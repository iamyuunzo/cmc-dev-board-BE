package com.cmc.board.post.domain;

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

    /**
     * 작성자 (User)
     */
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

    protected Post() {
        // JPA 기본 생성자
    }

    /**
     * 게시글 생성용 생성자
     */
    public Post(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // ===== 비즈니스 메서드 =====

    public void validateAuthor(Long loginUserId) {
        if (!this.author.getId().equals(loginUserId)) {
            throw new IllegalStateException("작성자만 삭제할 수 있습니다.");
        }
    }

    // ===== Getter =====

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
