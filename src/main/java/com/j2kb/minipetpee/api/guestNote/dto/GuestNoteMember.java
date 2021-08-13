package com.j2kb.minipetpee.api.guestNote.dto;

import lombok.Data;

@Data
public class GuestNoteMember {
    private int id;
    private String name;
    private String profileImageUrl;

    public GuestNoteMember(int id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}
