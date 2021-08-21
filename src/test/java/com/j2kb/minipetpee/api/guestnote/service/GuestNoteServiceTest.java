package com.j2kb.minipetpee.api.guestnote.service;

import com.j2kb.minipetpee.api.guestnote.controller.dto.request.SaveGuestNoteRequest;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.domain.repository.GuestNoteRepository;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Gender;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.api.setting.domain.repository.TabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

class GuestNoteServiceTest {

    private GuestNoteService guestNoteService;

    @Mock
    private GuestNoteRepository guestNoteRepository;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private TabRepository tabRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockGuestNoteRepository();
        guestNoteService = new GuestNoteService(guestNoteRepository, memberRepository, tabRepository);
    }


    private void mockGuestNoteRepository() {
        Member member = Member.builder()
                .id(1L)
                .email("emailExam@gmail.com")
                .password("11111")
                .name("파트라슈")
                .birthday(LocalDateTime.now())
                .species("dog")
                .personality("온순함")
                .gender(Gender.valueOf("MALE"))
                .profileImageUrl("profileUrl")
                .gateImageUrl("gateImageUrl")
                .deleted(false)
                .build();

        Tab tab = Tab.builder()
                .id(1L)
                .type(Type.GUEST)
                .visible(true)
                .homepee(Homepee.builder()
                        .id(1L)
                        .build())
                .build();

        List<GuestNote> guestNotes= new ArrayList<>();
        GuestNote guestNote = GuestNote.builder()
                .id(1L)
                .content("반가워요!")
                .visible(true)
                .tab(tab)
                .member(member)
                .build();
        guestNotes.add(guestNote);

        Slice<GuestNote> result = new SliceImpl<>(guestNotes);

        given(memberRepository.findById(any())).willReturn(Optional.of(member));
        given(tabRepository.findByHomepeeIdAndType(any(), eq(Type.GUEST))).willReturn(tab);
        given(guestNoteRepository.save(any())).willReturn(guestNote);
    }

    //저장 성공하는 로직
    @Test
    public void saveGuestNote() {
        Long homepeeId = 1L;
        SaveGuestNoteRequest saveGuestNote = new SaveGuestNoteRequest(1L, "반가워요!", true);
        GuestNote guestNote = guestNoteService.saveGuestNote(homepeeId, saveGuestNote);

        assertEquals(guestNote.getId(), 1L);
        assertEquals(guestNote.getContent(), saveGuestNote.getContent());
        assertEquals(guestNote.getTab().getHomepee().getId(), homepeeId);
        assertEquals(guestNote.getMember().getId(), saveGuestNote.getMemberId());
    }

    //member 못 찾을 경우 exception 테스트 로직

}