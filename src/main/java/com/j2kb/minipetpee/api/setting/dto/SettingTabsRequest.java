package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Type;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SettingTabsRequest {
    private Long id;
    private Type type;
    private boolean visible;
}
