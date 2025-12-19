package com.cmc.board.post.service;

import com.cmc.board.post.domain.Post;
import com.cmc.board.post.repository.PostRepository;
import com.cmc.board.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // 생성자 주입 (의존성 최소화)
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 게시글 생성
     *
     * @param author  이미 존재하는 사용자
     * @param title   게시글 제목
     * @param content 게시글 내용
     * @return 저장된 게시글
     */
    public Post create(User author, String title, String content) {

        Post post = new Post(author, title, content);

        return postRepository.save(post);
    }
}
