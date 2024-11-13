package com.datawise.social_media.repository;

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
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.id <> :userId AND u.id NOT IN (SELECT f.user.id FROM Follower f WHERE f.follower.id = :userId)")
    List<User> findUsersNotFollowing(@Param("userId") int userId);
}
