package com.datawise.social_media.controller;

import com.datawise.social_media.dto.ShareablePostResponse;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.service.PostService;
import com.datawise.social_media.service.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shareable-post")
public class SharablePostController {

    @Autowired
    private PostService postService;


    // This endpoint is authenticated and generates the shareable link
    @GetMapping("/link/{postId}")
    public ResponseEntity<String> generateShareableLink(@PathVariable int postId, HttpServletRequest request) {
        String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String shareableLink = postService.getShareableLink(postId, baseUrl);
        return ResponseEntity.ok(shareableLink);
    }

    @GetMapping("/view/{postId}")
    public ResponseEntity<ShareablePostResponse> viewShareablePost(@PathVariable Long postId) {
        ShareablePostResponse response = postService.getPostAndCommentsForShareableLink(postId);
        return ResponseEntity.ok(response);
    }
}
