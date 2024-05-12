package com.project.controller;

import com.project.domain.AuthInput;
import com.project.domain.MemberResponse;
import com.project.dto.MemberDto;
import com.project.security.JwtTokenProvider;
import com.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signup")
    public ResponseEntity<MemberResponse>
                    signup(@RequestBody AuthInput.SignUp request) {

        MemberDto memberDto = memberService.register(request);

        return ResponseEntity.ok(MemberResponse.fromDto(memberDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody AuthInput.SignIn request) {

        MemberDto memberDto = memberService.login(request);

        String token = jwtTokenProvider
                .generateToken(memberDto.getUsername(), memberDto.getRoles());

        return ResponseEntity.ok(token);
    }
}
