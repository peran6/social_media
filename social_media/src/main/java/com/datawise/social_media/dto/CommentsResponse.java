package com.datawise.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsResponse {
    private String content;
    private String comment_by;
    private LocalDateTime createdAt;
}
