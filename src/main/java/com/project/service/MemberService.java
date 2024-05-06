package com.project.service;

import com.project.domain.Auth;
import com.project.domain.Member;
import com.project.exception.AuthException;
import com.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetails loadByUsername(String username) {
        return memberRepository
                .findByUsername(username).orElseThrow(() ->
                        new AuthException("해당 ID의 유저가 없습니다"));
    }

    public Member register(Auth.SignUp member) {
        boolean exists = memberRepository.existsByUsername(member.getUsername());
        if (exists) {
            throw new RuntimeException("이미 존재하는 아이디 입니다");
        }

        return memberRepository.save(member.toEntity(passwordEncoder));
    }

    public Member login(Auth.SignIn member) {

        Member findMember = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new AuthException("해당 ID의 유저가 없습니다"));
        if (!passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {
            throw new AuthException("비밀번호가 일치하지 않습니다");
        }

        return findMember;
    }
}
