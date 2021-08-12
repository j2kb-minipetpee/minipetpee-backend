package com.j2kb.minipetpee.api.setting.dto;

import lombok.Data;

@Data
public class ProfileDto {

    private String name;
    private String birthday;
    private String species;
    private String personality;
    private String gender;
    private String profileImageUrl;
    private String gateImageUrl;
}
