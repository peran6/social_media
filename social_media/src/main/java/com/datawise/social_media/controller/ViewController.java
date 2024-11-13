package com.datawise.social_media.controller;

import com.datawise.social_media.dto.*;
import com.datawise.social_media.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/view")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @GetMapping("/feed")
    public List<PostsByFollowersResponse> getFeed() {
        return viewService.getPostsByFollowing();
    }

    @GetMapping("/myProfile")
    public List<PostsAndCommentsByUserResponse> getPostsAndCommentsByUser() {
        return viewService.getPostsAndCommentsByUser();
    }

    @GetMapping("/comments")
    public List<CommentsResponse> getAllCommetnsByUser() {
        return viewService.getAllCommetnsByUser();
    }

    @GetMapping("/latestComments")
    public List<LatestComments> getLatestComments() {
        return viewService.getLatestComments();
    }

    @GetMapping("/followers")
    public FollowersResponse getFollowers() {
        return viewService.getFollowers();
    }

    @GetMapping("/search")
    public List<UserResponse> search() {
        return viewService.search();
    }
}
