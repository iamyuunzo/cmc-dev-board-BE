package com.cmc.board.post.domain;

import com.cmc.board.user.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시글 작성자
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    /**
     * 게시글 제목
     */
    @Column(nullable = false)
    private String title;

    /**
     * 게시글 내용
     */
    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * 생성 시간
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // JPA 기본 생성자
    protected Post() {
    }

    /**
     * 게시글 생성자
     *
     * @param author  작성자
     * @param title   제목
     * @param content 내용
     */
    public Post(User author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
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

    /**
     * 게시글 수정
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
