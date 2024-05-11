package com.project.service.impl;

import com.project.domain.Auth;
import com.project.domain.Member;
import com.project.exception.ServiceException;
import com.project.repository.MemberRepository;
import com.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.project.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadByUsername(String username) {
        return memberRepository
                .findByUsername(username).orElseThrow(() ->
                        new ServiceException(USER_NOT_FOUND));
    }

    @Override
    public Member register(Auth.SignUp member) {
        boolean exists = memberRepository.existsByUsername(member.getUsername());
        if (exists) {
            throw new ServiceException(USER_ID_ALREADY_EXIST);
        }

        return memberRepository.save(member.toEntity(passwordEncoder));
    }

    @Override
    public Member login(Auth.SignIn member) {

        Member findMember = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new ServiceException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {
            throw new ServiceException(PASSWORD_NOT_MATCH);
        }

        return findMember;
    }
}
