package com.j2kb.minipetpee.api.star.controller.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class StarRequest {
    @NotBlank(message = "EMP8002")
    @Pattern(regexp = "^[0-9]+$", message = "EMP8002")
    private Long starMemberId;
}
