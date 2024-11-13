package com.datawise.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsByFollowersResponse {
    private Long postId;
    private String content;
    private String email;
    private LocalDateTime createdAt;
}
