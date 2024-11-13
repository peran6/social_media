package com.datawise.social_media.controller;

import com.datawise.social_media.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PreAuthorize("hasAnyAuthority('FREE', 'PREMIUM')")
    @PostMapping("/follow")
    public String followUser(@RequestParam Long targetId) {
        return followerService.followUser(targetId);
    }

    @PreAuthorize("hasAnyAuthority('FREE', 'PREMIUM')")
    @DeleteMapping("/unfollow")
    public String unfollowUser(@RequestParam Long targetId) {
        return followerService.unfollowUser(targetId);
    }
}
