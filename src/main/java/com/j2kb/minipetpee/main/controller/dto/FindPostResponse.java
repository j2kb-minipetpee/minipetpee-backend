package com.j2kb.minipetpee.main.controller.dto;

import lombok.Data;

@Data
public class FindPostResponse {
    private String memberName;
    private PostResponse postInfo;

    public FindPostResponse(String memberName, PostResponse postInfo) {
        this.memberName = memberName;
        this.postInfo = postInfo;
    }
}
