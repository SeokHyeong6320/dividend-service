package com.project.service;

import com.project.domain.Auth;
import com.project.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    UserDetails loadByUsername(String username);
    Member register(Auth.SignUp member);
    Member login(Auth.SignIn member);

}
