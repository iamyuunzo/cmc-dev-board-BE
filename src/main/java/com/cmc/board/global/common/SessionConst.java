package com.cmc.board.global.common;

/**
 * 세션에 저장할 key를 상수로 관리
 * - 문자열 하드코딩 방지
 */
public final class SessionConst {

    public static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    public static final String LOGIN_USER_ROLE = "LOGIN_USER_ROLE";

    private SessionConst() {
    }
}
