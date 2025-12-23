package com.cmc.board.post.repository;

import com.cmc.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시글 Repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}