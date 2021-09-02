package com.j2kb.minipetpee.api.setting.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateHomepeeRequest {

    @NotBlank(message = "EMP7006")
    @Size(min = 1, max = 20, message = "EMP7007")
    private String title;

    @NotNull(message = "EMP7016")
    private String gateImageUrl;
}
