package com.datawise.social_media.controller;

import com.datawise.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAnyAuthority('FREE', 'PREMIUM')")
    @PostMapping
    public String createPost(@RequestBody String content) {
        return postService.createPost(content);
    }
}
