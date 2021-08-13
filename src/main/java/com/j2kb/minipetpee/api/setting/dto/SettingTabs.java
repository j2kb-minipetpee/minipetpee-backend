package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Type;
import lombok.Data;

@Data
public class SettingTabs {
    private int id;
    private Type type;
    private boolean visible;
}
