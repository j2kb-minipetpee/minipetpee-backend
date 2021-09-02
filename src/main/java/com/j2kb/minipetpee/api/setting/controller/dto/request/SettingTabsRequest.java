package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SettingTabsRequest {
    private Long id;
    private Type type;
    private boolean visible;
}