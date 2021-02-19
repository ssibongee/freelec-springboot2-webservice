package com.freelecspringboot2webservice.service.post;

import com.freelecspringboot2webservice.web.dto.PostSaveRequestDto;
import com.freelecspringboot2webservice.web.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }
}
