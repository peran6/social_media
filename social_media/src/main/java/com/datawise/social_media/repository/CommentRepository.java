package com.datawise.social_media.repository;

import com.datawise.social_media.entity.Comment;
import com.datawise.social_media.entity.Post;
import com.datawise.social_media.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.user = :user AND c.post = :post")
    long countByUserAndPost(User user, Post post);

    Comment findTopByPostIdOrderByCreatedAtDesc(Long postId);
}
