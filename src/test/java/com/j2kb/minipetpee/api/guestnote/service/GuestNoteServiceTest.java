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
        mockMemberRepository();
        mockTabRepository();
        mockGuestNoteRepository();
        guestNoteService = new GuestNoteService(guestNoteRepository, memberRepository, tabRepository);
    }

    private void mockTabRepository() {
        Tab tab = Tab.builder()
                .id(1L)
                .type(Type.GUEST)
                .visible(true)
                .build();

        given(tabRepository.findByHomepeeIdAndType(any(), eq(Type.GUEST))).willReturn(tab);
    }

    private void mockMemberRepository() {
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

        given(memberRepository.findById(any())).willReturn(Optional.of(member));
    }

    private void mockGuestNoteRepository() {

        List<GuestNote> guestNotes= new ArrayList<>();
        GuestNote guestNote = GuestNote.builder()
                .id(1L)
                .content("반가워요!")
                .visible(true)
                .tab(Tab.builder()
                        .id(1L)
                        .type(Type.GUEST)
                        .visible(true)
                        .homepee(Homepee.builder()
                                .id(1L)
                                .build())
                        .build())
                .member(Member.builder()
                        .id(1L)
                        .name("petDog")
                        .profileImageUrl("profileUrl1")
                        .build())
                .build();
        guestNotes.add(guestNote);

        given(guestNoteRepository.findByHomepeeId(any())).willReturn(guestNotes);
        given(guestNoteRepository.save(any())).willReturn(guestNote);
    }

    //그냥 조회된 데이터를 넘겨주는 역할만 하므로 테스트 안해도 될것 같다.
    @Test
    public void findGuestNote() {
        List<GuestNote> guestNotes = guestNoteService.findGuestNote(1L);

        GuestNote guestNote = guestNotes.get(0);

        assertEquals(guestNote.getId(), 1L);
        assertEquals(guestNote.getMember().getId(), 1L);
        assertEquals(guestNote.getMember().getName(), "petDog");
        assertEquals(guestNote.getMember().getProfileImageUrl(), "profileUrl1");
        assertEquals(guestNote.getTab().getType(), Type.GUEST);
        assertEquals(guestNote.getContent(), "반가워요!");
        assertEquals(guestNote.isVisible(), true);
    }

    @Test
    public void saveGuestNote() {
        SaveGuestNoteRequest saveGuestNote = new SaveGuestNoteRequest(1L, "gggg", true);
        GuestNote guestNote = guestNoteService.saveGuestNote(1L, saveGuestNote);

        assertEquals(guestNote.getId(), 1L);
        assertEquals(guestNote.getContent(), saveGuestNote.getContent());
    }
}