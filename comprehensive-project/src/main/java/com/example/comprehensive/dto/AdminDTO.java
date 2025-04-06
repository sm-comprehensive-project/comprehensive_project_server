package com.example.comprehensive.dto;

import com.example.comprehensive.entity.Admin;
import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String username;
    private String role;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.role = admin.getRole();
    }
}
