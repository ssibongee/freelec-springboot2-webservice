package com.freelecspringboot2webservice.service.post;

import com.freelecspringboot2webservice.advice.exception.PostNotFoundException;
import com.freelecspringboot2webservice.web.dto.PostListResponseDto;
import com.freelecspringboot2webservice.web.dto.PostResponseDto;
import com.freelecspringboot2webservice.web.dto.PostSaveRequestDto;
import com.freelecspringboot2webservice.web.dto.PostUpdateRequestDto;
import com.freelecspringboot2webservice.web.domain.post.Post;
import com.freelecspringboot2webservice.web.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(String.valueOf(id))
        );

        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostResponseDto findById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(String.valueOf(id))
        );

        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(String.valueOf(id))
        );

        postRepository.delete(post);
    }
}
