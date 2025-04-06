package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    private String username;
    private String password;
    private String role = "ADMIN";
}
