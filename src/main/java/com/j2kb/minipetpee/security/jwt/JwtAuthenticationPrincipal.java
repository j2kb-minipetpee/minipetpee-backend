package com.j2kb.minipetpee.security.jwt;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Role;
import lombok.Getter;

@Getter
public class JwtAuthenticationPrincipal {
    private Long id;
    private String email;
    private String name;
    private Role role;

    public JwtAuthenticationPrincipal(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public JwtAuthenticationPrincipal(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.name();
        this.role = member.getRole();
    }
}
