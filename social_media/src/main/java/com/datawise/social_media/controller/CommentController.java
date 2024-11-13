package com.datawise.social_media.controller;

import com.datawise.social_media.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAnyAuthority('FREE', 'PREMIUM')")
    @PostMapping
    public String createComment(@RequestParam int postId, @RequestBody String content) {
        return commentService.createComment(postId, content);
    }
}
