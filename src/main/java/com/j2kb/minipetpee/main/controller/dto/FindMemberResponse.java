package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;

@Data
public class FindMemberResponse {
    private long id;
    private String name;
    private String profileImageUrl;
    private long homepeeId;

    public FindMemberResponse(long id, String name, String profileImageUrl, long homepeeId) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.homepeeId = homepeeId;
    }
}
