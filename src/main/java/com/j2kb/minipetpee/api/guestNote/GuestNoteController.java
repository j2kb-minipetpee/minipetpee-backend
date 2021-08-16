package com.j2kb.minipetpee.api.guestNote;

import com.j2kb.minipetpee.api.guestNote.dto.*;
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
    public ResponseEntity<FindGuestNotesResponse> findGuestNote(@PathVariable(name = "homepee-id") Long homepeeId) {
        GuestNoteMemberResponse mem1 = new GuestNoteMemberResponse(1L,"mem1", "url1111");
        GuestNoteMemberResponse mem2 = new GuestNoteMemberResponse(2L,"mem2", "url2222");
        GuestNoteMemberResponse mem3 = new GuestNoteMemberResponse(3L,"mem3", "url3333");
        FindGuestNoteResponse guestNote1 = new FindGuestNoteResponse(4L,mem1,"방명록1", true, LocalDateTime.now());
        FindGuestNoteResponse guestNote2 = new FindGuestNoteResponse(5L,mem2,"방명록2", true, LocalDateTime.now());
        FindGuestNoteResponse guestNote3 = new FindGuestNoteResponse(6L,mem3,"방명록3", false, LocalDateTime.now());

        List<FindGuestNoteResponse> guestNoteLists = new ArrayList<>();
        guestNoteLists.add(guestNote1);
        guestNoteLists.add(guestNote2);
        guestNoteLists.add(guestNote3);

        FindGuestNotesResponse guestNotesResponse = new FindGuestNotesResponse(guestNoteLists);

        return ResponseEntity.ok(guestNotesResponse);
    }

    //방명록 작성
    @PostMapping
    public ResponseEntity<SaveGuestNoteResponse> saveGuestNote(@PathVariable(name = "homepee-id") Long homepeeId,
                                                               @RequestBody SaveGuestNoteRequest guestNoteRequest) {
        GuestNoteMemberResponse guestNoteMember = new GuestNoteMemberResponse(guestNoteRequest.getMemberId(),"mem1",  "url1111");
        SaveGuestNoteResponse guestNoteResponse =
                new SaveGuestNoteResponse(1L, guestNoteMember, guestNoteRequest.getContent(), guestNoteRequest.isVisible(), LocalDateTime.now());
        return ResponseEntity.ok(guestNoteResponse);
    }

    //방명록 수정
    @PutMapping("/{guest-note-id}")
    public ResponseEntity<Void> updateGuestNote(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "guest-note-id") Long guestNoteId, @RequestBody UpdateGuestNoteRequest updateGuestNote) {
        return ResponseEntity.noContent().build();
    }

    //방명록 삭제
    @DeleteMapping("/{guest-note-id}")
    public ResponseEntity<Void> deleteGuestNote(@PathVariable(name = "homepee-id") Long homepeeId, @PathVariable(name = "guest-note-id") Long guestNoteId) {
        return ResponseEntity.noContent().build();
    }
}
