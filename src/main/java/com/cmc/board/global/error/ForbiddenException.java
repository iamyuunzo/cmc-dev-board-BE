package com.cmc.board.global.error;

/**
 * 권한 없음 (403)
 */
public class ForbiddenException extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
