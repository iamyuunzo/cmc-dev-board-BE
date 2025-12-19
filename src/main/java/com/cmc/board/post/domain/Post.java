package com.cmc.board.post.domain;

import com.cmc.board.user.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 게시글(Post) 엔티티
 *
 * - ERD의 posts 테이블에 대응
 * - 게시글은 반드시 작성자(User)를 가진다
 */
@Entity
@Table(name = "posts")
public class Post {

    /**
     * 게시글 PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시글 작성자
     *
     * - ERD: posts.user_id -> users.id
     * - Many Posts : One User
     * - 지연 로딩(LAZY)으로 성능 최적화
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
     *
     * - TEXT 타입 대응
     */
    @Lob
    @Column(nullable = false)
    private String content;

    /**
     * 생성 시간
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // ===== 기본 생성자 (JPA 필수) =====
    protected Post() {
        // JPA는 리플렉션을 사용하므로 기본 생성자가 필요함
    }

    // ===== 게시글 생성자 (필수 값 강제) =====
    /**
     * 게시글 생성자
     *
     * @param author 작성자(User)
     * @param title 게시글 제목
     * @param content 게시글 내용
     * @param createdAt 생성 시간
     */
    public Post(User author, String title, String content, LocalDateTime createdAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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

    // ===== 비즈니스 메서드 =====

    /**
     * 게시글 수정
     *
     * - 작성자 본인 여부는 Service 레이어에서 검증
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
