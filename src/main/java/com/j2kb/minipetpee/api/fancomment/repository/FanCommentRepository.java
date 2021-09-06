package com.j2kb.minipetpee.api.fancomment.repository;

import com.j2kb.minipetpee.api.fancomment.domain.FanComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FanCommentRepository extends JpaRepository<FanComment, Long> {

    List<FanComment> findAllByHomepeeId(Long homepeeId);
}
