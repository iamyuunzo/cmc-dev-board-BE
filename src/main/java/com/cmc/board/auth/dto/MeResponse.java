package com.cmc.board.auth.dto;

/**
 * 로그인 사용자 응답 DTO (디버그/확인용)
 */
public class MeResponse {

    private final Long id;
    private final String email;
    private final String role;

    public MeResponse(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}
