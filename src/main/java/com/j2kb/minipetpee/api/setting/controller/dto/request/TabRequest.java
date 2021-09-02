package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.j2kb.minipetpee.api.setting.domain.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TabRequest {

    @NotNull(message = "EMP7004")
    private Long id;

    @NotNull(message = "EMP7005")
    private boolean visible;
}
