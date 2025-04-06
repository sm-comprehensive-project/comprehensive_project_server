package com.example.comprehensive.entity;

import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inquiry")
@Data
public class Inquiry {
    @Id
    private String id;
    private String userEmail;
    private String title;
    private String message;
    private String reply;
    private LocalDateTime createdAt;
    private LocalDateTime repliedAt;
}
