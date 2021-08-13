package com.j2kb.minipetpee.api.guestNote;

import com.j2kb.minipetpee.api.guestNote.dto.*;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<FindGuestNoteListResponse> findGuestNote(@PathVariable(name = "homepee-id") int homepeeId) {
        GuestNoteMember mem1 = new GuestNoteMember(1,"mem1", "url1111");
        GuestNoteMember mem2 = new GuestNoteMember(2,"mem2", "url2222");
        GuestNoteMember mem3 = new GuestNoteMember(3,"mem3", "url3333");
        FindGuestNoteResponse guestNote1 = new FindGuestNoteResponse(0,mem1,"방명록1", true, LocalDateTime.now());
        FindGuestNoteResponse guestNote2 = new FindGuestNoteResponse(1,mem2,"방명록2", true, LocalDateTime.now());
        FindGuestNoteResponse guestNote3 = new FindGuestNoteResponse(2,mem3,"방명록3", false, LocalDateTime.now());

        List<FindGuestNoteResponse> guestNoteLists = new ArrayList<>();
        guestNoteLists.add(guestNote1);
        guestNoteLists.add(guestNote2);
        guestNoteLists.add(guestNote3);

        FindGuestNoteListResponse guestNoteListResponse = new FindGuestNoteListResponse(guestNoteLists);

        return ResponseEntity.ok(guestNoteListResponse);
    }

    //방명록 작성
    @PostMapping
    public ResponseEntity<SaveGuestNoteResponse> saveGuestNote(@PathVariable(name = "homepee-id") int homepeeId,
                                                               @RequestBody SaveGuestNoteRequest saveGuestNoteRequest) {
        GuestNoteMember mem = new GuestNoteMember(saveGuestNoteRequest.getMemberId(),"mem1", "url1111");
        SaveGuestNoteResponse saveGuestNoteResponse =
                new SaveGuestNoteResponse(0, mem, saveGuestNoteRequest.getContent(), saveGuestNoteRequest.isVisible(), LocalDateTime.now());
        return ResponseEntity.ok(saveGuestNoteResponse);
    }

    //방명록 수정
    @PutMapping("/{guest-note-id}")
    public ResponseEntity<Void> updateGuestNote(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "guest-note-id") int guestNoteId, @RequestBody UpdateGuestNoteRequest updateGuestNote) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //방명록 삭제
    @DeleteMapping("/{guest-note-id}")
    public ResponseEntity<Void> deleteGuestNote(@PathVariable(name = "homepee-id") int homepeeId, @PathVariable(name = "guest-note-id") int guestNoteId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
