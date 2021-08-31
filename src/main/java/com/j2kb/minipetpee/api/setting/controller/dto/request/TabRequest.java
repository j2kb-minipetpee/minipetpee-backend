package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TabRequest {
    private Long id;
    private boolean visible;
}
