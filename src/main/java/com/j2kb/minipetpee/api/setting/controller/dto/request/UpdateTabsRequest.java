package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateTabsRequest {
    @NotNull
    @Size(min = 3, message = "EMP7014")
    private List<TabRequest> tabs;
}
