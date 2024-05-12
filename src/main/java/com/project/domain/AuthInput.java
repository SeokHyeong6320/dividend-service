package com.project.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AuthInput {

    // 로그인
    @Data
    public static class SignIn {

        private String username;
        private String password;

    }

    // 회원가입
    @Data
    public static class SignUp {

        private String username;
        private String password;

        private List<String> roles;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .username(this.username)
                    .password(passwordEncoder.encode(this.password))
                    .roles(this.roles)
                    .build();
        }

    }
}
