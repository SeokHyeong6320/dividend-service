package com.project.domain;

import com.project.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

    private Long id;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;


    public static MemberResponse fromDto(MemberDto memberDto) {
        return MemberResponse.builder()
                .id(memberDto.getId())
                .username(memberDto.getUsername())
                .authorities(memberDto.getAuthorities())
                .build();
    }



}
