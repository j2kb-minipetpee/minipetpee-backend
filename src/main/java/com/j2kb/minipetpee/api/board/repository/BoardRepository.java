package com.j2kb.minipetpee.api.board.repository;

import com.j2kb.minipetpee.api.board.domain.BoardPost;
import com.j2kb.minipetpee.global.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardPost, Long> {
    Page<Post> findAllByOrderByViewCountDesc(Pageable pageable);

    Page<Post> findAllByTitleContainingOOrderByTitle(String title, Pageable pageable);
    Page<Post> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(String title, String content, Pageable pageable); // 제목 + 본문 검색
}
