package com.freelecspringboot2webservice.web.post;

import com.freelecspringboot2webservice.web.domain.post.Post;
import com.freelecspringboot2webservice.web.domain.post.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("PostRepository로 게시글을 저장하고 불러오는 테스트")
    void saveAndGet() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Post.builder()
                .author("abc@abc.com")
                .title(title)
                .content(content)
                .build());

        List<Post> posts = postRepository.findAll();

        Post post = posts.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("JPA Auditing을 사용하여 생성과 수정시간 자동 등록 테스트")
    void baseTimeEntityTest() {
        LocalDateTime now = LocalDateTime.of(2021, 2, 20, 0, 0, 0);
        postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Post> posts = postRepository.findAll();

        Post post = posts.get(0);

        assertThat(post.getCreatedTime()).isAfter(now);
        assertThat(post.getModifiedTime()).isAfter(now);

    }
}