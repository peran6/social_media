package com.datawise.social_media.repository;

import com.datawise.social_media.entity.Follower;
import com.datawise.social_media.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Hidden
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    Optional<Follower> findByFollowerAndUser(User follower, User user);

    @Query("SELECT f.user FROM Follower f WHERE f.follower = :follower")
    List<User> findFollowingUsers(@Param("follower") User follower);

    @Query("SELECT f.follower FROM Follower f WHERE f.user = :user")
    List<User> findFollowerUsers(@Param("user") User user);
}
