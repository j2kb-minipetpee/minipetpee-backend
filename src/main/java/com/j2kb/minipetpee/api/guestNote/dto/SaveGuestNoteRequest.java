package com.j2kb.minipetpee.api.guestNote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveGuestNoteRequest {

    private Long memberId;
    private String content;
    private boolean visible;
}
