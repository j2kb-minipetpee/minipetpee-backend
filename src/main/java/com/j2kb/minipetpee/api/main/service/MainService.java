package com.j2kb.minipetpee.api.main.service;

import com.j2kb.minipetpee.api.album.repository.AlbumRepository;
import com.j2kb.minipetpee.api.board.repository.BoardRepository;
import com.j2kb.minipetpee.api.member.domain.Member;
import com.j2kb.minipetpee.api.member.repository.MemberRepository;
import com.j2kb.minipetpee.global.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MainService {

    private final MemberRepository memberRepository;
    private final AlbumRepository albumRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<Post> findPopularPosts(Pageable pageable) {
        return albumRepository.findAllByOrderByViewCountDesc(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Member> searchMembers(String name, Pageable pageable) {
        return memberRepository.findAllByProfile_NameLikeOrderByProfile_NameAsc(name, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> searchPosts(String title, Pageable pageable) {
        return albumRepository.findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(title, pageable);
    }
}
