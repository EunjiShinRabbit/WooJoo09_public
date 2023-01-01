package com.WooJoo09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // 비밀번호를 DB에 그대로 저장하는 회원 정보가 노출 될 수 있으므로 암호화가 필수적으로 필요
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()			//cors 방지
                .csrf().disable()			//csrf 방지
                .formLogin().disable()		//기본 로그인페이지 없애기
                .headers().frameOptions().disable();

        return http.build();
    }
}