package com.j2kb.minipetpee.api.setting.controller.dto.response;

import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TabResponse {
    private final Long id;
    private final Type type;
    private final boolean visible;

    public TabResponse(Tab tab){
        this.id = tab.getId();
        this.type = tab.getType();
        this.visible = tab.isVisible();
    }
}
