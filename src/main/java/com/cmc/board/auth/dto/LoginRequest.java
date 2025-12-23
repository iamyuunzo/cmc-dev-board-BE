package com.cmc.board.auth.dto;

/**
 * 로그인 요청 DTO
 */
public class LoginRequest {

    private String email;
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
