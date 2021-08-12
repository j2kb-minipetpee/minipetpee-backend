package com.j2kb.minipetpee.api.guestNote.dto;

import lombok.Data;

@Data
public class NewGuestNoteRequestDto {

    private int memberId;
    private String content;
    private boolean visible;
}
