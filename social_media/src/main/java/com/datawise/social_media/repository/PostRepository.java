package com.datawise.social_media.repository;

import com.datawise.social_media.entity.Comment;
import com.datawise.social_media.entity.Post;
import com.datawise.social_media.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Hidden
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUserInOrderByCreatedAtDesc(List<User> users);

    List<Post> findByUserOrderByCreatedAtDesc(User user);

    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId ORDER BY c.createdAt DESC")
    List<Comment> findLatest100CommentsByPostId(@Param("postId") Long postId);
}
