package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reports")
public class Report {

    @Id
    private String id;

    private String reporterEmail; // 신고한 유저
    private String targetType; // 예: "product" 또는 "user"
    private String targetId; // 예: liveId or 사용자 ID

    private String reason;
    private String status; // 예: PENDING / RESOLVED
    private LocalDateTime reportedAt;
    private boolean handled;
}
