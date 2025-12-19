package com.cmc.board.user.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * 사용자(User) 엔티티
 *
 * - ERD의 users 테이블에 대응
 * - 게시글, 댓글, 북마크의 주체
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * PK
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 이메일
     * - 로그인 ID로 사용
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * 비밀번호
     * - 실제 서비스에서는 암호화 필요 (지금은 단순 문자열)
     */
    @Column(nullable = false)
    private String password;

    /**
     * 권한
     * - USER / ADMIN
     * - Step 3에서는 String으로 단순 처리
     */
    @Column(nullable = false)
    private String role;

    /**
     * 생성 시간
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // ===== 기본 생성자 (JPA 필수) =====
    protected User() {
    }

    // ===== 생성자 =====
    public User(String email, String password, String role, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    // ===== Getter =====
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
