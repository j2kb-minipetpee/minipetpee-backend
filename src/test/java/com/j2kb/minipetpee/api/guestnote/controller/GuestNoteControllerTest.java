package com.j2kb.minipetpee.api.guestnote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.guestnote.service.GuestNoteService;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(GuestNoteController.class)
class GuestNoteControllerTest {

    private final String BASE_URL = "/apis/{homepee-id}/guest/guest-notes";
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private GuestNoteService guestNoteService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GuestNoteController(guestNoteService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("방명록 조회")
    void findGuestNote() throws Exception {
        Pageable pageable =  PageRequest.of(0, 4, Sort.by("id").descending());
        Homepee homepee = Homepee.builder()
                .id(1L)
                .build();

        Tab tab = Tab.builder()
                .type(Type.GUEST)
                .visible(true)
                .homepee(homepee)
                .build();

        Profile profile = Profile.builder()
                .name("minipet")
                .profileImageUrl("profileUrl")
                .build();

        Member member = Member.builder()
                .id(1L)
                .profile(profile)
                .build();

        GuestNote guestNote1 = GuestNote.builder()
                .id(1L)
                .content("ggg")
                .visible(true)
                .tab(tab)
                .member(member)
                .build();

        List<GuestNote> guestNoteList = new ArrayList<>();
        guestNoteList.add(guestNote1);

        Slice<GuestNote> result = new SliceImpl<>(guestNoteList);
        given(guestNoteService.findGuestNotes(any(),eq(pageable))).willReturn(result);
        mockMvc.perform(get(BASE_URL, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].member.id").value(1L))
                .andExpect(jsonPath("$.[0].member.name").value("minipet"))
                .andExpect(jsonPath("$.[0].member.profileImageUrl").value("profileUrl"))
                .andExpect(jsonPath("$.[0].content").value("ggg"))
                .andExpect(jsonPath("$.[0].visible").value(true));
    }

    @Test
    @DisplayName("방명록 저장")
    void saveGuestNote() throws Exception {
        Homepee homepee = Homepee.builder()
                .id(1L)
                .build();

        Tab tab = Tab.builder()
                .type(Type.GUEST)
                .visible(true)
                .homepee(homepee)
                .build();

        Profile profile = Profile.builder()
                .name("minipet")
                .profileImageUrl("profileUrl")
                .build();

        Member member = Member.builder()
                .id(1L)
                .profile(profile)
                .build();

        GuestNote savedGuestNote = GuestNote.builder()
                .id(1L)
                .content("반가워요!")
                .visible(true)
                .tab(Tab.builder()
                        .type(Type.GUEST)
                        .visible(true)
                        .homepee(homepee)
                        .build())
                .member(member)
                .build();

        given(guestNoteService.saveGuestNote(eq(1L),any())).willReturn(savedGuestNote);

        mockMvc.perform(post(BASE_URL,1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"memberId\":1,\"content\":\"반가워요!\",\"visible\":true}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.member.id").value(3L))
                .andExpect(jsonPath("$.member.name").value("방명록글쓴이"))
                .andExpect(jsonPath("$.member.profileImageUrl").value("profileUrl"))
                .andExpect(jsonPath("$.content").value("반가워요!"))
                .andExpect(jsonPath("$.visible").value(true));

        verify(guestNoteService).saveGuestNote(eq(1L),any());
    }


    @Test
    @DisplayName("방명록 수정 content 길이 2미만")
    void updateGuestNoteValidationTest() throws Exception {
        Member member = Member.builder()
                .id(1L)
                .build();

        GuestNote updateGuestNote = GuestNote.builder()
                .id(2L)
                .member(member)
                .content("c")
                .visible(true)
                .build();

        mockMvc.perform(put(BASE_URL + "/{guest-note-id}", member.getId(), updateGuestNote.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateGuestNote)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("방명록 삭제")
    void deleteGuestNote() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/{guest-note-id}", 1L, 2L))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(guestNoteService).deleteGuestNote(2L);
    }
}