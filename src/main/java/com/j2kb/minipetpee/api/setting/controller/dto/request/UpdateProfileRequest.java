package com.j2kb.minipetpee.api.setting.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j2kb.minipetpee.api.member.domain.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {

    @NotBlank(message = "EMP7008")
    @Pattern(regexp="^[0-9a-zA-Z가-힣]*$", message = "EMP7015")
    @Size(min = 2, max = 10, message = "EMP7009")
    private String name;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;

    @NotNull
    @Size(max = 30, message = "EMP7012")
    private String species;

    @NotNull
    @Size(max = 30, message = "EMP7013")
    private String personality;

    @NotNull
    private Gender gender;

    @NotNull
    private String profileImageUrl;
}
