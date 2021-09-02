package com.j2kb.minipetpee.api.comment.repository;

import com.j2kb.minipetpee.api.comment.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndPostId(Long id, Long postId);

    @EntityGraph(attributePaths = {"member"})
    Page<Comment> findByPostId(Long id, Pageable pageable);
}
