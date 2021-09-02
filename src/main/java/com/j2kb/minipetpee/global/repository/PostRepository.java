package com.j2kb.minipetpee.global.repository;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByTabId(Long tabId, Pageable pagable);

    Optional<Post> findByIdAndTabId(Long id, Long tabId);

    // 조회순으로 게시글 받아오기 (조회수가 측정 불가한 앨범 제외)
    Page<Post> findByOrderByViewCountDesc(Pageable pageable);

    // 제목으로 검색 (앨범 및 게시글 일괄)
    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
