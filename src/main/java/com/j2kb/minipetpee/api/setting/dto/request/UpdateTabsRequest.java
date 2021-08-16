package com.j2kb.minipetpee.api.setting.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateTabsRequest {
    private List<SettingTabsRequest> tabs;
}
