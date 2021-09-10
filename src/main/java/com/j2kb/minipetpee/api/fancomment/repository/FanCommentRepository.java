package com.j2kb.minipetpee.api.fancomment.repository;

import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FanCommentRepository extends JpaRepository<FanComment, Long> {

    Page<FanComment> findAllByHomepeeId(Long homepeeId, Pageable pageable);

    Optional<FanComment> findByIdAndMemberId(Long homepeeId, Long memberId);
}
