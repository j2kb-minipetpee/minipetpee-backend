package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TabRequest {

    @NotNull(message = "EMP7004")
    private Long id;

    @NotNull(message = "EMP7005")
    private boolean visible;
}
