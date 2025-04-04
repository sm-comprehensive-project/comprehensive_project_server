package com.example.comprehensive.dto;

import com.example.comprehensive.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String nickname;
    private List<String> likedLiveIds;
    private List<String> recentWatchedIds;
    private List<String> watchedHistory;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.likedLiveIds = user.getLikedLiveIds();
        this.recentWatchedIds = user.getRecentWatchedIds();
        this.watchedHistory = user.getWatchedHistory();
    }
}