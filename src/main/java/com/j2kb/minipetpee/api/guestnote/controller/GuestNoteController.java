package com.j2kb.minipetpee.api.guestnote.controller;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.request.UpdateGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.controller.dto.response.GuestNoteResponse;
import com.j2kb.minipetpee.api.guestnote.controller.dto.response.GuestNoteMemberResponse;
import com.j2kb.minipetpee.api.guestnote.controller.dto.response.SaveGuestNoteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apis/{homepee-id}/guest/guest-notes")
public class GuestNoteController {

    //방명록 조회
    @GetMapping
    public ResponseEntity<List<GuestNoteResponse>> findGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId
    ) {
        GuestNoteMemberResponse mem1 = new GuestNoteMemberResponse(1L,"mem1", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        GuestNoteMemberResponse mem2 = new GuestNoteMemberResponse(2L,"mem2", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        GuestNoteMemberResponse mem3 = new GuestNoteMemberResponse(3L,"mem3", "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        GuestNoteResponse guestNote1 = new GuestNoteResponse(4L,mem1,"방명록1", true, LocalDateTime.now());
        GuestNoteResponse guestNote2 = new GuestNoteResponse(5L,mem2,"방명록2", true, LocalDateTime.now());
        GuestNoteResponse guestNote3 = new GuestNoteResponse(6L,mem3,"방명록3", false, LocalDateTime.now());

        List<GuestNoteResponse> guestNotes = new ArrayList<>();
        guestNotes.add(guestNote1);
        guestNotes.add(guestNote2);
        guestNotes.add(guestNote3);

        return ResponseEntity.ok(guestNotes);
    }

    //방명록 작성
    @PostMapping
    public ResponseEntity<SaveGuestNoteResponse> saveGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @RequestBody SaveGuestNoteRequest guestNoteRequest
    ) {
        GuestNoteMemberResponse guestNoteMember = new GuestNoteMemberResponse(guestNoteRequest.getMemberId(),"mem1",  "http://image.dongascience.com/Photo/2017/03/14900752352661.jpg");
        SaveGuestNoteResponse guestNoteResponse =
                new SaveGuestNoteResponse(1L, guestNoteMember, guestNoteRequest.getContent(), guestNoteRequest.isVisible(), LocalDateTime.now());
        return ResponseEntity.ok(guestNoteResponse);
    }

    //방명록 수정
    @PutMapping("/{guest-note-id}")
    public ResponseEntity<Void> updateGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "guest-note-id") Long guestNoteId,
            @RequestBody UpdateGuestNoteRequest updateGuestNote
    ) {
        return ResponseEntity.noContent().build();
    }

    //방명록 삭제
    @DeleteMapping("/{guest-note-id}")
    public ResponseEntity<Void> deleteGuestNote(
            @PathVariable(name = "homepee-id") Long homepeeId,
            @PathVariable(name = "guest-note-id") Long guestNoteId
    ) {
        return ResponseEntity.noContent().build();
    }
}
