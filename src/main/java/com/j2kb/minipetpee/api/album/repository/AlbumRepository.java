package com.j2kb.minipetpee.api.album.repository;

import com.j2kb.minipetpee.api.album.domain.AlbumPost;
import com.j2kb.minipetpee.global.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumRepository extends JpaRepository<AlbumPost, Long> {
    // 조회 - 조회수 내림차순
    Page<Post> findAllByOrderByViewCountDesc(Pageable pageable);

    // 검색 - 제목 포함(대소문자 무시) + 최신순
    Page<Post> findAllByTitleContainingIgnoreCaseOrderByCreatedAtDesc(String title, Pageable pageable);

}
