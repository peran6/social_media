package com.datawise.social_media.service;

import com.datawise.social_media.dto.CommentsResponse;
import com.datawise.social_media.dto.ShareablePostResponse;
import com.datawise.social_media.entity.Comment;
import com.datawise.social_media.entity.Post;
import com.datawise.social_media.entity.ShareablePost;
import com.datawise.social_media.entity.User;
import com.datawise.social_media.repository.PostRepository;
import com.datawise.social_media.repository.ShareablePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ShareablePostRepository shareablePostRepository;
    @Autowired
    private Utils utils;

    public String createPost(String content) {
        User user = utils.fetchUser();

        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + user.getEmail());
        }

        Post post = new Post();
        post.setContent(content, user.getRole());
        post.setUser(user);
        postRepository.save(post);

        return "Post created successfully";
    }

    public String getShareableLink(int postId, String baseUrl) {
        User user = utils.fetchUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().equals(user)) {
            throw new RuntimeException("You can only share your own posts");
        }

        ShareablePost shareablePost = new ShareablePost();
        shareablePost.setPost(post);
        shareablePost.setCreatedAt(LocalDateTime.now());
        shareablePostRepository.save(shareablePost);

        return baseUrl + "/api/shareable-post/view/" + postId;
    }

    public ShareablePostResponse getPostAndCommentsForShareableLink(Long postId) {
        ShareablePost shareablePost = shareablePostRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("This post is not shareable"));

        Post post = shareablePost.getPost();
        List<Comment> comments = postRepository.findLatest100CommentsByPostId(postId);
        List<CommentsResponse> commentResponses = comments.stream()
                .map(comment -> new CommentsResponse(
                        comment.getContent(),
                        comment.getUser().getEmail(),
                        comment.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return new ShareablePostResponse(post.getContent(), post.getCreatedAt(), commentResponses);
    }
}
