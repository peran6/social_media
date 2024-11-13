package com.datawise.social_media.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowersResponse {
    private List<UserResponse> following;
    private List<UserResponse> followers;
}
