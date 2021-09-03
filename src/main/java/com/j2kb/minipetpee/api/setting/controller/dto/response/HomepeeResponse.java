package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import lombok.Getter;

@Getter
public class HomepeeResponse {
    private final String title;
    private final String gateImageUrl;

    public HomepeeResponse(Homepee homepee) {
        this.title = homepee.getTitle();
        this.gateImageUrl = homepee.getGateImageUrl();
    }
}
