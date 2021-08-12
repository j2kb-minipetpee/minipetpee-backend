package com.j2kb.minipetpee.api.setting.dto;

import com.j2kb.minipetpee.domain.Gender;
import lombok.Data;

@Data
public class ViewProfileDto {
    private String email;
    private String name;
    private String birthday;
    private String species;
    private String personality;
    private Gender gender;   //enum은 참조해도 되겠지?
    private String profileImageUrl;
    private String gateImageUrl;

    public ViewProfileDto(String email, String name, String birthday, String species, String personality, Gender gender, String profileImageUrl, String gateImageUrl) {
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.species = species;
        this.personality = personality;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
        this.gateImageUrl = gateImageUrl;
    }
}
