package com.freelecspringboot2webservice.web;

import com.freelecspringboot2webservice.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postSave() {
        return "post-save";
    }
}

