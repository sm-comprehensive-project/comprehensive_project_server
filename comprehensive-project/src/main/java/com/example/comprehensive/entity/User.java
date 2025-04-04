package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private List<String> likedLiveIds = new ArrayList<>();
    private List<String> recentWatchedIds = new ArrayList<>();
    private List<String> watchedHistory = new ArrayList<>();
}
