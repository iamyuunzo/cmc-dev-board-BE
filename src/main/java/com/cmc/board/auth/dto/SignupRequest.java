package com.cmc.board.auth.dto;

/**
 * 회원가입 요청 DTO
 * - role은 입력 안 하면 USER로 처리
 */
public class SignupRequest {

    private String email;
    private String password;
    private String role;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}
