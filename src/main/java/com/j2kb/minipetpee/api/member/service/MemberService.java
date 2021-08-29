package com.j2kb.minipetpee.api.member.service;

import com.j2kb.minipetpee.api.homepee.domain.Homepee;
import com.j2kb.minipetpee.api.homepee.repository.HomepeeRepository;
import com.j2kb.minipetpee.api.member.controller.dto.LoginRequest;
import com.j2kb.minipetpee.api.member.controller.dto.SignUpRequest;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.api.setting.domain.Tab;
import com.j2kb.minipetpee.api.setting.domain.Type;
import com.j2kb.minipetpee.global.ErrorCode;
import com.j2kb.minipetpee.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final HomepeeRepository homepeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public void isExistedEmail(String email) {
        memberRepository.findByEmail(email).ifPresent(member -> {
            throw new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP1010);
        });
    }

    @Transactional
    public void saveMember(final SignUpRequest request) {
        Member member = request.toMember();
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);

        List<Tab> tabs = List.of(
                new Tab(null, Type.BOARD, true),
                new Tab(null, Type.ALBUM, true),
                new Tab(null, Type.GUEST, true)
        );
        Homepee homepee = new Homepee(member, tabs);
        homepeeRepository.save(homepee);
    }

    @Transactional(readOnly = true)
    public Member findByEmail(final LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, ErrorCode.EMP1014));

        member.isMatchPassword(passwordEncoder, request.getPassword());

        return member;
    }
}
