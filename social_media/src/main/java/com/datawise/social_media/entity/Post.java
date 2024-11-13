package com.datawise.social_media.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column(nullable = false, length = 3000)
    private String content;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public void setContent(String content, Role userRole) {
        if (userRole == Role.FREE && content.length() > 1000) {
            throw new IllegalArgumentException("Content exceeds the 1000 character limit for FREE users.");
        } else if (userRole == Role.PREMIUM && content.length() > 3000) {
            throw new IllegalArgumentException("Content exceeds the 3000 character limit for PREMIUM users.");
        }
        this.content = content;
    }
}
