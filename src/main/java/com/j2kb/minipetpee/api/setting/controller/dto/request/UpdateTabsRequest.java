package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateTabsRequest {
    @NotNull(message = "EMP7019")
    @Size(min = 3, message = "EMP7014")
    @Valid
    private List<TabRequest> tabs;
}
