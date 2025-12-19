package com.cmc.board.post.repository;

import com.cmc.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시글 Repository
 *
 * - JpaRepository를 상속받으면
 *   기본 CRUD 메서드(save, findById, findAll 등)가 자동 제공됨
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
