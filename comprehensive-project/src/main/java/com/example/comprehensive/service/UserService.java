package com.example.comprehensive.service;

import com.example.comprehensive.dto.UserDto;
import com.example.comprehensive.model.User;
import com.example.comprehensive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 모든 사용자 조회
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    // 새로운 사용자 추가
    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.getName(), userDto.getAge());
        userRepository.save(user);
        return new UserDto(user);
    }
}
