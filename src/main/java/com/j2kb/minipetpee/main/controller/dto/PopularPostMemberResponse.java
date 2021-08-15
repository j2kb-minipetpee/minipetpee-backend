package com.j2kb.minipetpee.main.controller.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PopularPostMemberResponse {
    private long id;
    private String name;
    private String profileImageUrl;

    public PopularPostMemberResponse(long id, String name, String profileImageUrl) {
        this.id = id;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }
}