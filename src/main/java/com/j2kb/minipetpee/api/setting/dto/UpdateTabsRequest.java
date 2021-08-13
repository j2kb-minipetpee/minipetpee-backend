package com.j2kb.minipetpee.api.setting.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateTabsRequest {
    private List<SettingTabs> tabs;
}
