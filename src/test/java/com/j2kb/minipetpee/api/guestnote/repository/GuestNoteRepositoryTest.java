package com.j2kb.minipetpee.api.guestnote.repository;

import com.j2kb.minipetpee.api.guestnote.domain.GuestNote;
import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.domain.Profile;
import com.j2kb.minipetpee.api.member.domain.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class GuestNoteRepositoryTest {

    @Autowired private GuestNoteRepository guestNoteRepository;
    @Autowired private HomepeeRepository homepeeRepository;
    @Autowired private MemberRepository memberRepository;

    @Test
    void findByHomepeeId() {
        Profile profile = Profile.builder()
                .name("파트라슈1")
                .build();

        Member homepeeOwner = Member.builder()
                .email("emailEx@gmail.com")
                .password("1111")
                .profile(profile)
                .build();
        memberRepository.save(homepeeOwner);

        Profile profile2 = Profile.builder()
                .name("파트라슈2")
                .build();
        Member guestMember = Member.builder()
                .email("emailEx2@gmail.com")
                .password("2222")
                .profile(profile2)
                .build();

        memberRepository.save(guestMember);

        Tab tab = Tab.builder()
                .type(Type.GUEST)
                .visible(true)
                .build();

        //cascade.all
        Homepee homepee = Homepee.builder()
                .member(homepeeOwner)
                .title("title11")
                .member(homepeeOwner)
                .visitCount(3)
                .build();
        //homepee.addTabs(tab);
        homepeeRepository.save(homepee);

        GuestNote guestNote = GuestNote.builder()
                .content("방명록 입니다.")
                .visible(true)
                .tab(tab)
                .member(guestMember)
                .build();

       guestNoteRepository.save(guestNote);

        PageRequest result = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"));
        //findGuestNote 리스트에서 tab의 visible 같은 것에 접근하려면 이것들은 프록시기 때문에 guest 조회하면서 함께 가져오기 위해 fetch join 사용
        Page<GuestNote> findGuestNote = guestNoteRepository.findAllByHomepeeId(homepee.getId(),result);

        List<GuestNote> content = findGuestNote.getContent();

        assertEquals(findGuestNote.getNumber(), 0);

        assertEquals(content.size(), 1);
        assertEquals(content.get(0).getId(), guestNote.getId());
        assertEquals(content.get(0).memberId(), guestMember.getId());
        assertEquals(content.get(0).memberName(), guestMember.getProfile().getName());
        assertEquals(content.get(0).memberProfileImageUrl(), homepeeOwner.getProfile().getProfileImageUrl());
        assertEquals(content.get(0).getContent(), guestNote.getContent());
        assertEquals(content.get(0).isVisible(), guestNote.isVisible());

        assertEquals(content.get(0).getTab().getHomepee().getId(), homepee.getId());
        assertEquals(content.get(0).getTab().isVisible(), guestNote.getTab().isVisible());
    }

}