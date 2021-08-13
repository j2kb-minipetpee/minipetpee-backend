package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Type;
import lombok.Data;

@Data
public class FindSettingsTab {
    private int id;
    private int homepeeId;
    private Type type;
    private boolean visible;

    public FindSettingsTab(int id, int homepeeId, Type type, boolean visible) {
        this.id = id;
        this.homepeeId = homepeeId;
        this.type = type;
        this.visible = visible;
    }
}
