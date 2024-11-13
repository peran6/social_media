package com.datawise.social_media.service;

import com.datawise.social_media.entity.Follower;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.FollowerRepository;
import com.datawise.social_media.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Transactional
    public String followUser(Long targetId) {
        User follower = utils.fetchUser();

        if (follower.getId() == targetId) {
            return "You cannot follow yourself.";
        }

        // Find the user to be followed
        Optional<User> targetUserOpt = userRepository.findUserById(targetId);
        if (targetUserOpt.isEmpty()) {
            throw new IllegalArgumentException("Target user not found with ID: " + targetId);
        }
        User targetUser = targetUserOpt.get();

        boolean alreadyFollowing = follower.getFollowing().stream()
                .anyMatch(f -> f.getUser().equals(targetUser));
        if (alreadyFollowing) {
            return "You are already following this user.";
        }

        Follower followerEntity = new Follower();
        followerEntity.setUser(targetUser);
        followerEntity.setFollower(follower);
        followerRepository.save(followerEntity);

        return "You are now following user with ID " + targetId;
    }


    @Transactional
    public String unfollowUser(Long targetId) {
        User follower = utils.fetchUser();

        Optional<User> targetUserOpt = userRepository.findUserById(targetId);
        if (targetUserOpt.isEmpty()) {
            throw new IllegalArgumentException("Target user not found with ID: " + targetId);
        }
        User targetUser = targetUserOpt.get();

        Optional<Follower> followerEntityOpt = followerRepository.findByFollowerAndUser(follower, targetUser);
        if (followerEntityOpt.isEmpty()) {
            return "You are not following this user.";
        }

        followerRepository.delete(followerEntityOpt.get());

        return "You have unfollowed " + targetId;
    }
}
