// src/main/java/com/example/comprehensive/entity/User.java
package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String loginType;
    private String address;
    private Gender gender;
    private LocalDate birthDate;

    private List<String> likedLiveIds = new ArrayList<>();
    private List<String> recentWatchedIds = new ArrayList<>();
    private List<String> watchedHistory = new ArrayList<>();
    private List<String> clickedItems = new ArrayList<>();
    private List<String> interestedCategories = new ArrayList<>();
    private List<String> searchHistory = new ArrayList<>();

    // ↓ 아래 두 필드를 추가하세요 ↓
    /** 파이썬 스크립트가 upsert 해 놓은 추천 리스트 */
    private List<Recommendation> recommendations = new ArrayList<>();
    /** recommendations 가 언제 갱신되었는지 */
    private LocalDateTime recommendationsUpdatedAt;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    /** user.recommendations 배열의 요소 타입 **/
    @Data
    public static class Recommendation {
        private String liveId;
        private double score;
    }
}
