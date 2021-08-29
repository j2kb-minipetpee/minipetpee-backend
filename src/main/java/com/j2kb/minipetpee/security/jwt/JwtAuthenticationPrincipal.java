package com.j2kb.minipetpee.security.jwt;

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

    public JwtAuthenticationPrincipal(Long id, String email, String name, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
