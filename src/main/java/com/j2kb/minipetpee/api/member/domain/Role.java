package com.j2kb.minipetpee.api.member.domain;

import lombok.Getter;

import java.util.List;

@Getter
public enum Role {
    OWNER("ROLE_OWNER", List.of(Authority.values()));

    private String role;

    private List<Authority> authorities;

    Role(final String role, final List<Authority> authorities) {
        this.role = role;
        this.authorities = authorities;
    }
}
