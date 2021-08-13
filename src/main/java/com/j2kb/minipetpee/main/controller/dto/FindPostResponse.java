package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;

@Data
public class FindPostResponse {
    private String memberName;
    private PostInfo postInfo;

    public FindPostResponse(String memberName, PostInfo postInfo) {
        this.memberName = memberName;
        this.postInfo = postInfo;
    }
}
