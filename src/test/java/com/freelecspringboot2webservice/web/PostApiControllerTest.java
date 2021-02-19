package com.freelecspringboot2webservice.web;

import com.freelecspringboot2webservice.web.dto.PostSaveRequestDto;
import com.freelecspringboot2webservice.web.dto.PostUpdateRequestDto;
import com.freelecspringboot2webservice.web.domain.post.Post;
import com.freelecspringboot2webservice.web.domain.post.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("Post가 성공적으로 등록되면 Id를 반환한다.")
    void savePostTest() throws Exception {
        String title = "title";
        String content = "content";

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isGreaterThan(0L);

        List<Post> posts = postRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo(title);
        assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("게시글 변경이 완료되면 기존의 게시글 제목과 내용이 변경된다.")
    void updatePostTest() throws Exception {
        Post savedPost = postRepository.save(Post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto =
                PostUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> posts = postRepository.findAll();
        assertThat(posts.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(posts.get(0).getContent()).isEqualTo(expectedContent);

    }
}