package com.datawise.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsAndCommentsByUserResponse {
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    private List<CommentsResponse> comments;
}
