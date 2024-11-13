package com.datawise.social_media.service;

import com.datawise.social_media.entity.Comment;
import com.datawise.social_media.entity.Post;
import com.datawise.social_media.entity.Role;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.CommentRepository;
import com.datawise.social_media.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Utils utils;

    public String createComment(int postId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with ID: " + postId));

        User loginUser = utils.fetchUser();
        long userCommentCount = commentRepository.countByUserAndPost(loginUser, post);
        if (loginUser.getRole() == Role.FREE && userCommentCount >= 5) {
            return "You can only comment up to 5 times on a post as a FREE user.";
        }

        Comment comment = new Comment();
        comment.setUser(loginUser);
        comment.setPost(post);
        comment.setContent(content);
        commentRepository.save(comment);

        return "Comment added successfully.";
    }
}
