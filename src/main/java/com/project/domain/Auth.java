package com.project.domain;

import com.project.constants.Role;
import lombok.Data;

import java.util.List;

public class Auth {

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



    }
}
