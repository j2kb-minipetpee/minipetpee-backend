package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TabResponse {
    private final Long id;
    // 필요 없을 것 같아 일단 주석 처리 해두겠습니다!
//    private final Long homepeeId;
    private final Type type;
    private final boolean visible;

    public TabResponse(Tab tab){
        this.id = tab.getId();
//        this.homepeeId = tab.homepeeId();
        this.type = tab.getType();
        this.visible = tab.isVisible();
    }
}
