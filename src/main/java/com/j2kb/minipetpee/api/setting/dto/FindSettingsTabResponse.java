package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Type;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindSettingsTabResponse {
    private final Long id;
    private final Long homepeeId;
    private final Type type;
    private final boolean visible;

}
