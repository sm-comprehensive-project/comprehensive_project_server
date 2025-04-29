package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "kakao_schedule")
public class KakaoSchedule {
    @Id
    private String id;
    private String liveUrl;
    private String channelUrl;
    private String title;
    private String thumbnail;
    private String seller;
    private String platform;
    private List<LocalDateTime> dates;
}
