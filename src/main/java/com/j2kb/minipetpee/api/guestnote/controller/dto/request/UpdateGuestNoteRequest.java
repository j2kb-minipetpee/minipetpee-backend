package com.j2kb.minipetpee.api.guestnote.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateGuestNoteRequest {
    private Long id;
    private Long memberId;
    private String content;
    private boolean visible;
}
