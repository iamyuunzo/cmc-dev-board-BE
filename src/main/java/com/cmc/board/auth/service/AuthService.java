package com.cmc.board.auth.service;

import com.cmc.board.auth.dto.LoginRequest;
import com.cmc.board.auth.dto.MeResponse;
import com.cmc.board.auth.dto.SignupRequest;
import com.cmc.board.global.common.SessionConst;
import com.cmc.board.global.error.exception.BadRequestException;
import com.cmc.board.global.error.exception.NotFoundException;
import com.cmc.board.global.error.exception.UnauthorizedException;
import com.cmc.board.user.domain.User;
import com.cmc.board.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(SignupRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new BadRequestException("email/password는 필수입니다.");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("이미 존재하는 이메일입니다.");
        }

        String role = (request.getRole() == null || request.getRole().isBlank())
                ? "USER"
                : request.getRole();

        User user = new User(request.getEmail(), request.getPassword(), role, LocalDateTime.now());
        userRepository.save(user);
    }

    public void login(LoginRequest request, HttpSession session) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new UnauthorizedException("비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute(SessionConst.LOGIN_USER_ID, user.getId());
        session.setAttribute(SessionConst.LOGIN_USER_ROLE, user.getRole());
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public MeResponse me(HttpSession session) {
        Object userIdObj = session.getAttribute(SessionConst.LOGIN_USER_ID);
        if (userIdObj == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        Long userId = (Long) userIdObj;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        return new MeResponse(user.getId(), user.getEmail(), user.getRole());
    }
}
