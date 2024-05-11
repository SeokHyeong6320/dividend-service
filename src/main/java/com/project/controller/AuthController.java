package com.project.controller;

import com.project.domain.Auth;
import com.project.domain.Member;
import com.project.security.TokenProvider;
import com.project.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberServiceImpl memberServiceImpl;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) {
        Member member = memberServiceImpl.register(request);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {

        Member member = memberServiceImpl.login(request);
        String token = tokenProvider
                .generateToken(member.getUsername(), member.getRoles());

        return ResponseEntity.ok(token);
    }
}
