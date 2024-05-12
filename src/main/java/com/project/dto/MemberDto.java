package com.project.dto;

import com.project.domain.Member;
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
public class MemberDto {

    private Long id;

    private String username;

    private String password;

    private List<String> roles;

    private Collection<? extends GrantedAuthority> authorities;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRoles())
                .authorities(member.getAuthorities())
                .build();
    }
}
