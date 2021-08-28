package com.j2kb.minipetpee.global.repository;

import com.j2kb.minipetpee.global.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndPostId(Long id, Long postId);
}
