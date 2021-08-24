package com.j2kb.minipetpee.api.main.service;

import com.j2kb.minipetpee.api.board.repository.PostRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MainService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Page<Post> findPopularPosts(Pageable pageable) {
        return postRepository.findAllByOrderByViewCountDesc(pageable);
    }

    public Page<Member> searchMembers(String name, Pageable pageable) {
        return memberRepository.findAllByName(name, pageable);
    }

    public Page<Post> searchPosts(String title, Pageable pageable) {
        return postRepository.findAllByTitleContainingIgnoreCase(title, pageable);
    }
}
