package com.j2kb.minipetpee.main.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FindPopularPostResponse {
    private long homepeeId;
    private PopularPost post;
    private PopularPostMember member;

    public FindPopularPostResponse(long homepeeId, PopularPost post, PopularPostMember member) {
        this.homepeeId = homepeeId;
        this.post = post;
        this.member = member;
    }
}
