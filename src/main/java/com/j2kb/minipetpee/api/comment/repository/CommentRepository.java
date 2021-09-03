package com.j2kb.minipetpee.global.repository;

import com.j2kb.minipetpee.global.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"member"})
    Page<Comment> findByPostId(Long id, Pageable pageable);
}
