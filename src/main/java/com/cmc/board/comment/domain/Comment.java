package com.cmc.board.comment.domain;

import com.cmc.board.post.domain.Post;
import com.cmc.board.user.domain.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 댓글 / 대댓글 엔티티
 * - parent가 있으면 대댓글
 * - parent가 null이면 최상위 댓글
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글이 달린 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    // 부모 댓글 (대댓글용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parent;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected Comment() {}

    // 댓글 / 대댓글 공용 생성자
    public Comment(Post post, User author, Comment parent, String content) {
        this.post = post;
        this.author = author;
        this.parent = parent;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // ===== 비즈니스 로직 =====
    public void validateAuthor(Long loginUserId) {
        if (!author.getId().equals(loginUserId)) {
            throw new IllegalStateException("작성자만 삭제할 수 있습니다.");
        }
    }

    // ===== Getter =====
    public Long getId() {
        return id;
    }
}
