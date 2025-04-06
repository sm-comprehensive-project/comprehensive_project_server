package com.example.comprehensive.config;

import com.example.comprehensive.service.AdminDetailsService;
import com.example.comprehensive.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final AdminDetailsService adminDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
            AdminDetailsService adminDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
        this.adminDetailsService = adminDetailsService;
    }

    // 사용자용 시큐리티
    @Bean
    @Order(1)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/user/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/register", "/api/user/login").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(customUserDetailsService)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 관리자용 시큐리티
    @Bean
    @Order(2)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/admin/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/admin/login", "/api/admin/register").permitAll()
                        .anyRequest().hasRole("ADMIN"))
                .userDetailsService(adminDetailsService)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 공통 비밀번호 인코더
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
