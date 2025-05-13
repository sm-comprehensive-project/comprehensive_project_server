package com.example.comprehensive.dto;

import com.example.comprehensive.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

    private String email;
    private String nickname;
    private String loginType;
    private String address;
    private User.Gender gender;
    private LocalDate birthDate;
    private List<String> likedLiveIds;
    private List<String> recentWatchedIds;
    private List<String> watchedHistory;
    private List<String> clickedItems;
    private List<String> interestedCategories;
    private List<String> searchHistory;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.loginType = user.getLoginType();
        this.address = user.getAddress();
        this.gender = user.getGender();
        this.birthDate = user.getBirthDate();
        this.likedLiveIds = user.getLikedLiveIds();
        this.recentWatchedIds = user.getRecentWatchedIds();
        this.watchedHistory = user.getWatchedHistory();
        this.clickedItems = user.getClickedItems();
        this.interestedCategories = user.getInterestedCategories();
        this.searchHistory = user.getSearchHistory();
    }
}
