package com.j2kb.minipetpee.api.guestnote.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class UpdateGuestNoteRequest {

    @NotNull(message = "EMP6004")
    private Long memberId;

    @NotNull(message = "EMP6005")
    @Size(max = 200, message = "EMP6006")
    private String content;

    private boolean visible;

    public UpdateGuestNoteRequest(Long memberId, String content, boolean visible) {
        this.memberId = memberId;
        this.content = content;
        this.visible = visible;
    }
}
