package com.cmc.board.user.repository;

import com.cmc.board.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 사용자 Repository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
