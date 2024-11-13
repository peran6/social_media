package com.datawise.social_media.repository;

import com.datawise.social_media.entity.ShareablePost;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Hidden
@Repository
public interface ShareablePostRepository extends JpaRepository<ShareablePost, Long> {
    Optional<ShareablePost> findByPostId(Long postId);
}
