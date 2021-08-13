package com.j2kb.minipetpee.api.setting.dto;

import lombok.Data;

import java.util.List;

@Data
public class FindSettingsResponse {
    FindSettingsProfile profile;
    List<FindSettingsTab> tabs;

    public FindSettingsResponse(FindSettingsProfile profile, List<FindSettingsTab> tabs) {
        this.profile = profile;
        this.tabs = tabs;
    }
}
