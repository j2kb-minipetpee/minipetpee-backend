package com.j2kb.minipetpee.api.homepee.repository;

import com.j2kb.minipetpee.api.homepee.domain.FanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FanCommentRepository extends JpaRepository<FanComment, Long> {

    List<FanComment> findAllByHomepeeId(Long homepeeId);
}
