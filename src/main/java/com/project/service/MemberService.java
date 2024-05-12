package com.project.service;

import com.project.domain.AuthInput;
import com.project.dto.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    UserDetails loadByUsername(String username);
    MemberDto register(AuthInput.SignUp member);
    MemberDto login(AuthInput.SignIn member);

}
