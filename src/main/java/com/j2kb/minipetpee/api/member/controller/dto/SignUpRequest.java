package com.j2kb.minipetpee.api.member.controller.dto;

import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {
    @NotNull(message = "EMP1006")
    @Email(regexp = "[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$", message = "EMP1011")
    private String email;

    @NotNull(message = "EMP1008")
    @Size(min = 8, message = "EMP1012")
    private String password;

    @NotNull(message = "EMP1005")
    @Size(min = 2, max = 35, message = "EMP1013")
    private String name;

    public Member toMember() {
        final Profile profile = new Profile(name, null, null, null, null, null);
        return new Member(null, email, password, profile);
    }
}
