package com.j2kb.minipetpee.star.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindFanResponse {
    private long id;
    private long memberId;
    private String name;
    private String profileImageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;


    public FindFanResponse(long id, long memberId, String name, String profileImageUrl, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
    }
}
