package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String email;
    private String password;
    private String nickname;

    private String loginType; // e.g., "basic", "google"
    private String address;
    private Gender gender;
    private LocalDate birthDate;

    private List<String> likedLiveIds = new ArrayList<>();
    private List<String> recentWatchedIds = new ArrayList<>();
    private List<String> watchedHistory = new ArrayList<>();
    private List<String> clickedItems = new ArrayList<>();
    private List<String> interestedCategories = new ArrayList<>();
    private List<String> searchHistory = new ArrayList<>();

    public enum Gender {
        MALE, FEMALE, OTHER
    }
}
