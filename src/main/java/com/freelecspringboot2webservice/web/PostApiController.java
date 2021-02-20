package com.freelecspringboot2webservice.web;

import com.freelecspringboot2webservice.service.post.PostService;
import com.freelecspringboot2webservice.web.dto.PostResponseDto;
import com.freelecspringboot2webservice.web.dto.PostSaveRequestDto;
import com.freelecspringboot2webservice.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id")
    public Long delete(@PathVariable Long id) {
        postService.delete(id);
        return id;
    }
}
