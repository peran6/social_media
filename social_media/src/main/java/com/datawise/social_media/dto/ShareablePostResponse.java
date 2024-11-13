package com.datawise.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShareablePostResponse {
    private String postContent;
    private LocalDateTime postCreatedAt;
    private List<CommentsResponse> comments;
}
