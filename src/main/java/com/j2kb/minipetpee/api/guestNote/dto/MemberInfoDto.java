package com.j2kb.minipetpee.api.guestNote.dto;

import lombok.Data;

@Data
public class MemberInfoDto {
    private int id;
    private String name;
    private String profileImageUrl;

    public MemberInfoDto(int id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
