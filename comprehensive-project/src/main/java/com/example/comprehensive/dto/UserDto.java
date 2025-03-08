package com.example.comprehensive.dto;

import com.example.comprehensive.model.User;

public class UserDto {
    private String id;
    private String name;
    private int age;

    public UserDto() {
    }

    public UserDto(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // 엔티티 -> DTO 변환 생성자
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
