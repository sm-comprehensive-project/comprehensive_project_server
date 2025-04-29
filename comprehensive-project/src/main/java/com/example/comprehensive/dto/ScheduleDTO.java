package com.example.comprehensive.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ScheduleDTO {
    private String id;
    private String liveUrl;
    private String channelUrl;
    private String title;
    private String thumbnail;
    private String seller;
    private String platform;
    private List<LocalDateTime> dates;
}
