package com.cmc.board.post.service;

import com.cmc.board.post.domain.Post;
import com.cmc.board.post.repository.PostRepository;
import com.cmc.board.user.domain.User;
import com.cmc.board.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 게시글 생성
     */
    public Post create(Long loginUserId, String title, String content) {
        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        Post post = new Post(user, title, content);
        return postRepository.save(post);
    }

    /**
     * 게시글 단건 조회
     */
    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    /**
     * 게시글 삭제 (작성자 검증)
     */
    public void delete(Long postId, Long loginUserId) {
        Post post = findById(postId);

        // 🔥 작성자 검증
        post.validateAuthor(loginUserId);

        postRepository.delete(post);
    }
}