package com.j2kb.minipetpee.api.homepee.service;

import com.j2kb.minipetpee.api.homepee.domain.FanComment;
import com.j2kb.minipetpee.api.homepee.repository.FanCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FanCommentService {

    private final FanCommentRepository fanCommentRepository;

    public List<FanComment> findAllByHomepeeId(Long homepeeId) {
        return fanCommentRepository.findAllByHomepeeId(homepeeId);
    }
}
