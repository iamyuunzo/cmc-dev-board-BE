package com.cmc.board.global.config;

import com.cmc.board.global.common.SessionConst;
import com.cmc.board.global.error.exception.ForbiddenException;
import com.cmc.board.global.error.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ADMIN만 Swagger 접근 가능하도록 제한하는 인터셉터
 */
public class AdminSwaggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        Object roleObj = request.getSession()
                .getAttribute(SessionConst.LOGIN_USER_ROLE);

        if (roleObj == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        String role = roleObj.toString();
        if (!"ADMIN".equals(role)) {
            throw new ForbiddenException("ADMIN 권한만 접근 가능합니다.");
        }

        return true;
    }
}
