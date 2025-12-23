package com.cmc.board.comment.service;

import com.cmc.board.comment.domain.Comment;
import com.cmc.board.comment.repository.CommentRepository;
import com.cmc.board.post.domain.Post;
import com.cmc.board.post.repository.PostRepository;
import com.cmc.board.user.domain.User;
import com.cmc.board.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 댓글 / 대댓글 생성
     */
    public Comment create(Long postId,
                          Long loginUserId,
                          Long parentCommentId,
                          String content) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        Comment parent = null;
        if (parentCommentId != null) {
            parent = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."));
        }

        Comment comment = new Comment(post, user, parent, content);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제 (작성자 검증)
     */
    public void delete(Long commentId, Long loginUserId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다."));

        comment.validateAuthor(loginUserId);
        commentRepository.delete(comment);
    }
}
