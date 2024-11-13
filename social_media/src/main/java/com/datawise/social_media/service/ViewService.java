package com.datawise.social_media.service;

import com.datawise.social_media.dto.*;
import com.datawise.social_media.entity.Comment;
import com.datawise.social_media.entity.Post;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.CommentRepository;
import com.datawise.social_media.repository.FollowerRepository;
import com.datawise.social_media.repository.PostRepository;
import com.datawise.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Utils utils;

    public List<PostsByFollowersResponse> getPostsByFollowing() {
        // Fetch the logged-in user
        User user = utils.fetchUser();
        List<User> followingUsers = followerRepository.findFollowingUsers(user);
        List<Post> posts = postRepository.findByUserInOrderByCreatedAtDesc(followingUsers);

        return posts.stream()
                .map(post -> new PostsByFollowersResponse(post.getId(), post.getContent(), post.getUser().getEmail(), post.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<PostsAndCommentsByUserResponse> getPostsAndCommentsByUser() {
        User user = utils.fetchUser();
        List<Post> posts = postRepository.findByUserOrderByCreatedAtDesc(user);

        List<PostsAndCommentsByUserResponse> responses = posts.stream()
                .map(post -> {
                    List<Comment> comments = postRepository.findLatest100CommentsByPostId(post.getId());

                    List<CommentsResponse> commentResponses = comments.stream()
                            .map(comment -> new CommentsResponse(
                                    comment.getContent(),
                                    comment.getUser().getEmail(),
                                    comment.getCreatedAt()
                            ))
                            .collect(Collectors.toList());

                    return new PostsAndCommentsByUserResponse(
                            post.getId(),
                            post.getContent(),
                            post.getCreatedAt(),
                            commentResponses
                    );
                })
                .collect(Collectors.toList());
        return responses;
    }

    public List<CommentsResponse> getAllCommetnsByUser() {
        User user = utils.fetchUser();
        List<Post> posts = postRepository.findByUserOrderByCreatedAtDesc(user);

        List<CommentsResponse> allComments = posts.stream()
                .flatMap(post -> post.getComments().stream())
                .map(comment -> new CommentsResponse(
                        comment.getContent(),
                        comment.getUser().getEmail(),
                        comment.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return allComments;
    }

    public List<LatestComments> getLatestComments() {
        User user = utils.fetchUser();
        List<User> followingUsers = followerRepository.findFollowingUsers(user);
        followingUsers.add(user);
        List<Post> posts = postRepository.findByUserInOrderByCreatedAtDesc(followingUsers);

        List<LatestComments> latestComments = posts.stream()
                .map(post -> {
                    Comment latestComment = commentRepository.findTopByPostIdOrderByCreatedAtDesc(post.getId());

                    if (latestComment != null) {
                        CommentsResponse commentResponse = new CommentsResponse(
                                latestComment.getContent(),
                                latestComment.getUser().getEmail(),
                                latestComment.getCreatedAt()
                        );
                        return new LatestComments(post.getId(), commentResponse);
                    }
                    return null;
                })
                .filter(latestComment -> latestComment != null)
                .collect(Collectors.toList());

        return latestComments;
    }

    public FollowersResponse getFollowers() {
        User user = utils.fetchUser();

        List<UserResponse> following = followerRepository.findFollowingUsers(user).stream()
                .map(followedUser -> new UserResponse(followedUser.getId(), followedUser.getEmail()))
                .collect(Collectors.toList());

        List<UserResponse> followers = followerRepository.findFollowerUsers(user).stream()
                .map(followerUser -> new UserResponse(followerUser.getId(), followerUser.getEmail()))
                .collect(Collectors.toList());

        return new FollowersResponse(following, followers);
    }

    public List<UserResponse> search() {
        User user = utils.fetchUser();
        List<User> usersNotFollowing = userRepository.findUsersNotFollowing(user.getId());

        return usersNotFollowing.stream()
                .map(u -> new UserResponse(u.getId(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
