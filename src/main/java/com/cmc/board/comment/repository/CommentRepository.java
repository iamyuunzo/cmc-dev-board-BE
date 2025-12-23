package com.cmc.board.comment.repository;

import com.cmc.board.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 댓글 Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
