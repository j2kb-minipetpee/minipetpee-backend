package com.j2kb.minipetpee.api.guestnote.service;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.domain.repository.GuestNoteRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.domain.repository.TabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GuestNoteService {

    private final GuestNoteRepository guestNoteRepository;
    private final MemberRepository memberRepository;
    private final TabRepository tabRepository;

    public List<GuestNote> findGuestNote(Long homepeeId) {
         return guestNoteRepository.findByHomepeeId(homepeeId);
    }

    @Transactional
    public GuestNote saveGuestNote(Long homepeeId, SaveGuestNoteRequest guestNoteRequest) {
        //member 객체 찾기 -> 예외처리 필요
        Member member = memberRepository.findById(guestNoteRequest.getMemberId()).orElse(null);
        //Tab 찾기
        Tab tab = tabRepository.findByHomepeeIdAndType(homepeeId, Type.GUEST);

        GuestNote guestNote = GuestNote.createGuestNote(guestNoteRequest, tab, member);

        return guestNoteRepository.save(guestNote);
    }
}
