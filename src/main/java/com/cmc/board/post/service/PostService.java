package com.cmc.board.post.service;

import com.cmc.board.post.domain.Post;
import com.cmc.board.post.repository.PostRepository;
import com.cmc.board.user.domain.User;
import com.cmc.board.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 게시글 비즈니스 로직 담당 Service
 */
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 생성자 주입 (권장 방식)
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * 게시글 생성
     *
     * @param author 작성자(User)
     * @param title 게시글 제목
     * @param content 게시글 내용
     * @return 저장된 게시글
     */
    public Post create(User author, String title, String content) {

        User savedUser = userRepository.save(author);

        Post post = new Post(
                author,
                title,
                content,
                LocalDateTime.now()
        );

        return postRepository.save(post);
    }
}
